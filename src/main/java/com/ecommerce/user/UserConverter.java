package com.ecommerce.user;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public User toEntity(UserRequest request, String authority, PasswordEncoder passwordEncoder) {
    User user = new User();
    user.setName(request.name());
    user.setSurname(request.surname());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setAuthority(authority);
    return user;
  }

  public UserResponse toResponse(User entity) {
    return new UserResponse(
        entity.getId(),
        entity.getName(),
        entity.getSurname(),
        entity.getEmail(),
        entity.getAuthority());
  }

  public void updateEntity(
      User user,
      UserRequest request,
      org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
    user.setName(request.name());
    user.setSurname(request.surname());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));
  }
}
