package blue.builder.me.auth.service;

import blue.builder.me.user.dto.UserDTO;
import org.json.simple.JSONObject;

public interface AuthService {
    public JSONObject signup(UserDTO userDTO);
}
