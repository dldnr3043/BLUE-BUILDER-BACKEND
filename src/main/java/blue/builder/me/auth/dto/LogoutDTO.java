package blue.builder.me.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogoutDTO {
    private String email;

    @Builder
    public LogoutDTO(String email) {
        this.email = email;
    }
}
