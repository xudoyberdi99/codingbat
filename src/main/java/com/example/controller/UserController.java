package com.example.controller;

import com.example.entity.User;
import com.example.payload.ApiResponse;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid  @RequestBody User user){
        ApiResponse apiResponse = userService.addUser(user);
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getUserList(){
        List<User> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email){
        Optional<User> user = userService.getUser(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        ApiResponse apiResponse = userService.deleteUser(email);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> editUser( @Valid @PathVariable String email,@RequestBody User user){
        ApiResponse apiResponse = userService.editUser(email, user);
        return ResponseEntity.ok(apiResponse);
    }
}
