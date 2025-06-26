package com.ecommerce.auth;

import com.ecommerce.auth.dto.AuthResponse;
import com.ecommerce.user.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

  public AuthResponse toResponse(User user, String message, String token) {
    return new AuthResponse(
        user.getId(), user.getName(), user.getEmail(), user.getAuthority(), message, token);
  }
}
