package blue.builder.me.user.service;

import blue.builder.me.user.domain.User;
import blue.builder.me.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


}
