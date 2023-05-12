package com.av.user.service;

import com.av.user.request.UserCreateRequest;
import com.av.user.response.UserResponse;

import java.util.List;

public interface UserService {
    Long insert(UserCreateRequest request);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
    List<UserResponse> findAllBySorting();
    void deleteById(Long id);
    void deleteByPhoneNumber(String phoneNumber);
}
