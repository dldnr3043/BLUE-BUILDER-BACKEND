package blue.builder.me.auth.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtResponseEnum {
    EXPIRED_REFRESH_TOKEN(-100, "REFRESH TOKEN이 만료 되었습니다. 다시 로그인 해주세요."),
    INVALID_SIGNATURE(-200, "잘못된 JWT 서명입니다."),
    EXPIRED_TOKEN(-300, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(-400, "지원되지 않는 JWT 토큰입니다."),
    WRONG_TOKEN(-500, "JWT 토큰이 잘못되었습니다."),
    HAS_PROBLEM_TOKEN(-600, "올바르지 않은 토큰입니다."),
    CORRECT_TOKEN(-700, "올바른 토큰입니다.");


    private final int code;
    private final String msg;
}
