package blue.builder.me.auth.service;

import blue.builder.me.auth.repository.RefreshTokenRepository;
import blue.builder.me.auth.util.TokenProvider;
import blue.builder.me.user.domain.User;
import blue.builder.me.user.dto.UserDTO;
import blue.builder.me.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    @Transactional
    public JSONObject signup(UserDTO userDTO) {
        JSONObject retObject = new JSONObject();

        if(userRepository.existsById(userDTO.getEmail())) {
            retObject.put("ERROR_FLAG", true);
            retObject.put("ERROR_MSG", "이미 가입되어 있는 사용자입니다.");
            return retObject;
        }
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
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
}
