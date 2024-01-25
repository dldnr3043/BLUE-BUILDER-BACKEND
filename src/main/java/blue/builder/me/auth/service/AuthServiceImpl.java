package blue.builder.me.auth.service;

import blue.builder.me.auth.repository.RefreshTokenRepository;
import blue.builder.me.auth.util.TokenProvider;
import blue.builder.me.user.domain.User;
import blue.builder.me.user.dto.UserDTO;
import blue.builder.me.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public UserDTO signup(UserDTO userDTO) {
        if(userRepository.existsById(userDTO.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 사용자입니다.");
        }
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .build();

        User savedUser = userRepository.save(user);

        return UserDTO.builder()
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .name(savedUser.getName())
                .build();
    }
}