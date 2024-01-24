package blue.builder.me.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class UserDTO {
    private String email;
    private String password;
    private String name;
}


