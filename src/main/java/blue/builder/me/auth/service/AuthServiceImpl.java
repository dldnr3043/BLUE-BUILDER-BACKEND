package blue.builder.me.auth.service;

import blue.builder.me.auth.dto.LoginDTO;
import blue.builder.me.auth.dto.SignupDTO;
import blue.builder.me.auth.dto.TokenDTO;
import blue.builder.me.auth.util.TokenProvider;
import blue.builder.me.user.domain.User;
import blue.builder.me.user.dto.UserDTO;
import blue.builder.me.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    @Transactional
    public JSONObject signup(SignupDTO signupDTO) {
        JSONObject retObject = new JSONObject();

        if(userRepository.existsById(signupDTO.getEmail())) {
            retObject.put("ERROR_FLAG", true);
            retObject.put("ERROR_MSG", "이미 가입되어 있는 사용자입니다.");
            return retObject;
        }
        User user = User.builder()
                .email(signupDTO.getEmail())
                .password(passwordEncoder.encode(signupDTO.getPassword()))
                .name(signupDTO.getName())
                .build();

        User savedUser = userRepository.save(user);

        retObject.put("ERROR_FLAG", false);
        retObject.put("ERROR_MSG", "");
        retObject.put("DATA", UserDTO.builder()
                                    .email(savedUser.getEmail())
                                    .password(savedUser.getPassword())
                                    .name(savedUser.getName())
                                    .build());
        return retObject;
    }

    @Override
    @Transactional
    public JSONObject login(LoginDTO loginDTO) {
        JSONObject retObject = new JSONObject();

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginDTO.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDTO tokenDTO = tokenProvider.generateToken(authentication);

        // 4-1. 기존에 있던 token info 삭제
        // 4-2. token info 저장 to redis
        setTokenInfoRedis(loginDTO, tokenDTO);

        // 5. 토큰 발급
        retObject.put("ERROR_FLAG", false);
        retObject.put("ERROR_MSG", "");
        retObject.put("DATA", tokenDTO);
        return retObject;
    }

    private void setTokenInfoRedis(LoginDTO loginDTO, TokenDTO tokenDTO) {
        // 1. 파라메터 세팅
        String accessTokenKey = loginDTO.getEmail() + ":" + "accessToken";
        String accessTokenKeyExpirationTime = loginDTO.getEmail() + ":" + "accessTokenExpirationTime";
        String refreshTokenKey = loginDTO.getEmail() + ":" + "refreshToken";
        String refreshTokenKeyExpirationTime = loginDTO.getEmail() + ":" + "refreshTokenExpirationTime";

        // 2. 기존 token info 삭제
        if(Boolean.TRUE.equals(redisTemplate.hasKey(accessTokenKey))) redisTemplate.delete(accessTokenKey);
        if(Boolean.TRUE.equals(redisTemplate.hasKey(accessTokenKeyExpirationTime))) redisTemplate.delete(accessTokenKeyExpirationTime);
        if(Boolean.TRUE.equals(redisTemplate.hasKey(refreshTokenKey))) redisTemplate.delete(refreshTokenKey);
        if(Boolean.TRUE.equals(redisTemplate.hasKey(refreshTokenKeyExpirationTime))) redisTemplate.delete(refreshTokenKeyExpirationTime);

        // 3. token info 저장
        redisTemplate.opsForValue().set(accessTokenKey, tokenDTO.getAccessToken());
        redisTemplate.opsForValue().set(accessTokenKeyExpirationTime, Long.toString(tokenDTO.getAccessTokenExpirationTime()));
        redisTemplate.opsForValue().set(refreshTokenKey, tokenDTO.getRefreshToken());
        redisTemplate.opsForValue().set(refreshTokenKeyExpirationTime, Long.toString(tokenDTO.getRefreshTokenExpirationTime()));
    }

}