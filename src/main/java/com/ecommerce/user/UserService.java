package com.ecommerce.user;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import java.util.List;

public interface UserService {

  List<UserResponse> findAllUsers();

  UserResponse findUserById(long id);

  UserResponse saveUser(UserRequest request);

  UserResponse updateUser(long id, UserRequest request);

  UserResponse deleteUserById(long id);
}
