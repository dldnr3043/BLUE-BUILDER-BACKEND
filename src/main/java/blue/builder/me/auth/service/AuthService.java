package blue.builder.me.auth.service;

import blue.builder.me.auth.dto.LoginDTO;
import blue.builder.me.auth.dto.SignupDTO;
import blue.builder.me.auth.dto.TokenDTO;
import blue.builder.me.user.dto.UserDTO;
import org.json.simple.JSONObject;

public interface AuthService {
    public JSONObject signup(SignupDTO signupDTO);
    public JSONObject login(LoginDTO loginDTO);
}
