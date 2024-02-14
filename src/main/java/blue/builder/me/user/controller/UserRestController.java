package blue.builder.me.user.controller;

import blue.builder.me.auth.dto.SignupDTO;
import blue.builder.me.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;
    @PostMapping("/api/user/select")
    public JSONObject selectUser(@RequestBody JSONObject reqObject) {
        String accessToken = reqObject.get("accessToken").toString();

        JSONObject userInfo = userService.selectUser(accessToken);

        return userInfo;
    }
}
