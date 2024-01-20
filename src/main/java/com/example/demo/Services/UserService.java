package com.example.demo.Services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User userLogin(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public User registerUser(String username, String password){

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            if(userRepository.findByUsername(username).isPresent()){
                return null;
            } else {
                return userRepository.save(user);
            }


    }
}

