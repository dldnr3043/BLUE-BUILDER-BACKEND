package blue.builder.me.auth.controller;

import blue.builder.me.auth.dto.LoginDTO;
import blue.builder.me.auth.dto.SignupDTO;
import blue.builder.me.auth.dto.TokenDTO;
import blue.builder.me.auth.service.AuthService;
import blue.builder.me.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthRestController {
    @Autowired
    private AuthService authService;

    @PostMapping("/api/signup")
    public JSONObject signup(@RequestBody SignupDTO signupDTO) {
        JSONObject retObject = authService.signup(signupDTO);

        return retObject;
    }

    @PostMapping("/api/login")
    public JSONObject login(@RequestBody LoginDTO loginDTO) {
        JSONObject retObject = authService.login(loginDTO);

        return retObject;
    }

    @PostMapping("/api/auth/token/reissue")
    public JSONObject reissueToken(@RequestBody JSONObject params) {
        JSONObject retObject = authService.reissueToken(params);

        return retObject;
    }

}
