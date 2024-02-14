package blue.builder.me.user.service;

import blue.builder.me.auth.util.TokenProvider;
import blue.builder.me.user.domain.User;
import blue.builder.me.user.dto.UserDTO;
import blue.builder.me.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Override
    public JSONObject selectUser(String accessToken) {
        JSONObject retObject = new JSONObject();
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String email = authentication.getName();

        User user = userRepository.findById(email)
                .orElseThrow(()-> new RuntimeException(email + " 사용자의 세션이 없습니다."));

        UserDTO userDTO = UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();

        retObject.put("ERROR_FLAG", false);
        retObject.put("DATA", UserDTO.builder()
                                .email(user.getEmail())
                                .name(user.getName())
                                .build());

        return retObject;
    }
}
