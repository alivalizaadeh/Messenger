package com.av.user.service;

import com.av.user.entity.User;
import com.av.user.exception.UserNotFoundException;
import com.av.user.repository.UserRepository;
import com.av.user.request.UserCreateRequest;
import com.av.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long insert(UserCreateRequest request){
        User user = User.builder().
                firstname(request.firstName()).
                lastname(request.lastName()).
                bio(request.bio()).
                username(request.username()).
                phoneNumber(request.phoneNumber()).
                isVerified(true).
                profilePicture(null).
                savedMessages(new ArrayList<>()).
                sendMessages(new ArrayList<>()).
                allMessages(new ArrayList<>()).
                unreadMessages(new ArrayList<>()).
                contacts(new ArrayList<>()).
                build();
        return userRepository.save(user).getId();
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
        return new UserResponse(user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getBio(),
                user.getPhoneNumber(),
                user.getProfilePicture());
    }
}
