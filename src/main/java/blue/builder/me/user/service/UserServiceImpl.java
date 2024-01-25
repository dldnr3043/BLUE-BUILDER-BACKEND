package blue.builder.me.user.service;

import blue.builder.me.user.domain.User;
import blue.builder.me.user.dto.UserDTO;
import blue.builder.me.user.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public JSONObject signUp(UserDTO userDTO) {
        JSONObject retObject = new JSONObject();

        Optional<User> findUser = userRepository.findById(userDTO.getEmail());

        findUser.ifPresentOrElse(user->{
            // 1. 존재하면 error flag : true
            retObject.put("ERROR_FLAG", true);
            retObject.put("ERROR_MSG", "이미 등록된 이메일입니다.");
        }, ()->{
            // 2. 존재하지 않으면 save
            User saveUser = User.builder()
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .name(userDTO.getName())
                    .build();
            userRepository.save(saveUser);
            retObject.put("ERROR_FLAG", false);
            retObject.put("ERROR_MSG", "");
            retObject.put("DATA", saveUser);
        });

        return retObject;
    }
}
