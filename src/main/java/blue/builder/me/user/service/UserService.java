package blue.builder.me.user.service;

import org.json.simple.JSONObject;

public interface UserService {
    JSONObject selectUser(String accessToken);
}
