package blue.builder.me.auth.service;

import blue.builder.me.user.dto.UserDTO;

public interface AuthService {
    public UserDTO signup(UserDTO userDTO);
}
