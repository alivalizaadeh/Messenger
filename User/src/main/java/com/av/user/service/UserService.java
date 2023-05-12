package com.av.user.service;

import com.av.user.request.UserCreateRequest;
import com.av.user.response.UserResponse;

public interface UserService {
    Long insert(UserCreateRequest request);
    UserResponse findById(Long id);
}
