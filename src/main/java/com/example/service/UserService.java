package com.example.service;

import com.example.entity.User;
import com.example.payload.ApiResponse;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApiResponse addUser(User user1){
        boolean existsByEmail = userRepository.existsByEmail(user1.getEmail());
        if (existsByEmail){
            return new ApiResponse("bunday user mavjud",false);
        }
        User user=new User();
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        userRepository.save(user);
        return new ApiResponse("created account", true);
    }

    public ApiResponse editUser(String email,User user1){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()){
            return new ApiResponse("bunday user mavjud emas", false);
        }
        User user = optionalUser.get();
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());

        userRepository.save(user);
        return new ApiResponse("edit user success",true);
    }

    public List<User> getUserList(){
        List<User> all = userRepository.findAll();
        return all;
    }
    public Optional<User> getUser(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return Optional.of(user.orElseGet(User::new));

    }
    public ApiResponse deleteUser(String email){

            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()){
                return new ApiResponse("Error!!",false);
            }
            userRepository.delete(user.get());
            return new ApiResponse("delete User", true);

    }
}
