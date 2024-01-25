package blue.builder.me.user.service;

import blue.builder.me.user.domain.User;
import blue.builder.me.user.dto.UserDTO;
import org.json.simple.JSONObject;

public interface UserService {
    public JSONObject signUp(UserDTO userDTO);
}
