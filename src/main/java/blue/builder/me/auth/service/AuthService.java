package blue.builder.me.auth.service;

import blue.builder.me.auth.dto.LoginDTO;
import blue.builder.me.auth.dto.SignupDTO;
import org.json.simple.JSONObject;

public interface AuthService {
    public JSONObject signup(SignupDTO signupDTO);
    public JSONObject login(LoginDTO loginDTO);
    public JSONObject reissueToken(JSONObject params);
}
