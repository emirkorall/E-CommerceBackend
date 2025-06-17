package com.ecommerce.service;

import com.ecommerce.dto.request.UserRequest;
import com.ecommerce.dto.response.UserResponse;
import com.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponse> findAllUsers();

    UserResponse findUserById(long id);

    UserResponse saveUser(UserRequest request);

    UserResponse updateUser(long id, UserRequest request);

    UserResponse deleteUserById(long id);
}
