package blue.builder.me.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupDTO {
    private String email;
    private String password;
    private String name;

    @Builder
    public SignupDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}


