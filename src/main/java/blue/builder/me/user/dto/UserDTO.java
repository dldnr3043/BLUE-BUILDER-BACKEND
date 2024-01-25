package blue.builder.me.user.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {
    private String email;
    private String password;
    private String name;

    @Builder
    public UserDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}


