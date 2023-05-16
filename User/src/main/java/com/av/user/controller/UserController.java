package com.av.user.controller;

import com.av.user.exception.User.UserNotFoundException;
import com.av.user.request.UserInsertRequest;
import com.av.user.response.UserResponse;
import com.av.user.service.UserService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@DynamicUpdate
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/insert")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserInsertRequest request)
            throws UserNotFoundException {
        return new ResponseEntity<>(userService.insert(request) , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id)
            throws UserNotFoundException{
        return new ResponseEntity<>(userService.findById(id) , HttpStatus.FOUND);
    }

    @GetMapping("/{phoneNumber}p")
    public ResponseEntity<UserResponse> getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber)
            throws UserNotFoundException{
        return new ResponseEntity<>(userService.findByPhoneNumber(phoneNumber) , HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
