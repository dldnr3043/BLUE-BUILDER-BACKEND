package blue.builder.me.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpirationTime;
    private Long refreshTokenExpirationTime;

    @Builder
    public TokenDTO(String grantType, String accessToken, String refreshToken, Long accessTokenExpirationTime, Long refreshTokenExpirationTime) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }
}
