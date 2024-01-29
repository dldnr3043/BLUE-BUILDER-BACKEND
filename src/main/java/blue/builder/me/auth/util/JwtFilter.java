package blue.builder.me.auth.util;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "Refresh-Token";
    public static final String BEARER_PREFIX = "Bearer ";
    private final RedisTemplate<String, String> redisTemplate;
    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, IOException {

        // 1. Request Header 에서 토큰을 꺼냄
        String accessToken = resolveAccessToken(request);
        String refreshToken = resolveRefreshToken(request);

        // access token 유효성 검사
        if (StringUtils.hasText(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            int validationAccessTokenCode = tokenProvider.validateToken(accessToken);

            // 올바른 access token인 경우
            if(validationAccessTokenCode == JwtResponseEnum.CORRECT_TOKEN.getCode()) {
                Claims claims = tokenProvider.parseClaims(accessToken);
                response.addHeader("access-token", accessToken);
                response.addHeader("access-token-expiration-time", Long.toString(claims.getExpiration().getTime()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 만료된 access token인 경우
            else if(validationAccessTokenCode == JwtResponseEnum.EXPIRED_TOKEN.getCode()) {
                if (StringUtils.hasText(refreshToken)) {
                    int validationRefreshTokenCode = tokenProvider.validateToken(refreshToken);

                    // 올바른 refresh token인 경우
                    if(validationRefreshTokenCode == JwtResponseEnum.CORRECT_TOKEN.getCode()) {
                        String accessTokenRedisKey = authentication.getName() + ":accessToken";
                        String accessTokenExpirationTimeRedisKey = authentication.getName() + ":accessTokenExpirationTime";
                        String newAccessToken = tokenProvider.recreateAccessToken(accessToken);
                        Claims claims = tokenProvider.parseClaims(newAccessToken);

                        if(Boolean.TRUE.equals(redisTemplate.hasKey(accessTokenRedisKey))) redisTemplate.delete(accessTokenRedisKey);
                        if(Boolean.TRUE.equals(redisTemplate.hasKey(accessTokenExpirationTimeRedisKey))) redisTemplate.delete(accessTokenExpirationTimeRedisKey);

                        redisTemplate.opsForValue().set(accessTokenRedisKey, newAccessToken);
                        redisTemplate.opsForValue().set(accessTokenExpirationTimeRedisKey, Long.toString(claims.getExpiration().getTime()));

                        response.addHeader("access-token", newAccessToken);
                        response.addHeader("access-token-expiration-time", Long.toString(claims.getExpiration().getTime()));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    // refresh token이 만료되었을 경우
                    else if(validationRefreshTokenCode == JwtResponseEnum.EXPIRED_TOKEN.getCode()) {
                        log.error(JwtResponseEnum.EXPIRED_REFRESH_TOKEN.getCode() + " : " + JwtResponseEnum.EXPIRED_REFRESH_TOKEN.getMsg());
                    }
                    // 올바른 refresh token이 아닌 경우
                    else {
                        log.error(JwtResponseEnum.HAS_PROBLEM_TOKEN.getCode() + " : " + JwtResponseEnum.HAS_PROBLEM_TOKEN.getMsg());
                    }
                }
            }
            // 올바른 access token이 아닌 경우
            else {
                log.error(JwtResponseEnum.HAS_PROBLEM_TOKEN.getCode() + " : " + JwtResponseEnum.HAS_PROBLEM_TOKEN.getMsg());
            }
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.split(" ")[1].trim();
        }
        return null;
    }
    
    private String resolveRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(REFRESH_HEADER);
        if(StringUtils.hasText(refreshToken) && refreshToken.startsWith(BEARER_PREFIX)) {
            return refreshToken.split(" ")[1].trim();
        }
        return null;
    }
}
