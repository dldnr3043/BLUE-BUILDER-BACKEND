package blue.builder.me.auth.util;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, IOException {

        // 1. Request Header 에서 토큰을 꺼냄
        String accessToken = resolveAccessToken(request);

        // access token 유효성 검사
        if (StringUtils.hasText(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            int validationAccessTokenCode = tokenProvider.validateToken(accessToken);

            // 올바른 access token인 경우
            if(validationAccessTokenCode == JwtResponseEnum.CORRECT_TOKEN.getCode()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 잘못된 JWT 서명인 경우
            else if(validationAccessTokenCode == JwtResponseEnum.INVALID_SIGNATURE.getCode()) {
                log.error("[ " + request.getRequestURI() + " ]" + JwtResponseEnum.INVALID_SIGNATURE.getCode() + " - " + JwtResponseEnum.INVALID_SIGNATURE.getMsg());
            }
            // 만료된 토큰인 경우
            else if(validationAccessTokenCode == JwtResponseEnum.EXPIRED_TOKEN.getCode()) {
                log.error("[ " + request.getRequestURI() + " ]" + JwtResponseEnum.EXPIRED_TOKEN.getCode() + " - " + JwtResponseEnum.EXPIRED_TOKEN.getMsg());
            }
            // 지원되지 않는 JWT 토큰인 경우
            else if(validationAccessTokenCode == JwtResponseEnum.UNSUPPORTED_TOKEN.getCode()) {
                log.error("[ " + request.getRequestURI() + " ]" + JwtResponseEnum.UNSUPPORTED_TOKEN.getCode() + " - " + JwtResponseEnum.UNSUPPORTED_TOKEN.getMsg());
            }
            // 올바르지 않은 토큰인 경우
            else if(validationAccessTokenCode == JwtResponseEnum.HAS_PROBLEM_TOKEN.getCode()){
                log.error("[ " + request.getRequestURI() + " ]" + JwtResponseEnum.HAS_PROBLEM_TOKEN.getCode() + " - " + JwtResponseEnum.HAS_PROBLEM_TOKEN.getMsg());
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
}
