package com.av.user.service;

import com.av.user.request.UserInsertRequest;
import com.av.user.request.UserUpdateRequest;
import com.av.user.response.UserResponse;

import java.util.List;

public interface UserService {
    Long insert(UserInsertRequest request);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
    List<UserResponse> findAllBySorting();
    void deleteById(Long id);
    void deleteByPhoneNumber(String phoneNumber);
    Long updateById(UserUpdateRequest request);
    Long updateByPhoneNumber(UserUpdateRequest request);
}
