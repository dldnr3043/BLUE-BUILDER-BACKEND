package blue.builder.me.user.controller;

import blue.builder.me.auth.dto.SignupDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserRestController {
    @PostMapping("/api/test")
    public JSONObject signup(@RequestBody SignupDTO signupDTO) {
        JSONObject retObject = new JSONObject();

        retObject.put("test", 123);

        return retObject;
    }
}
