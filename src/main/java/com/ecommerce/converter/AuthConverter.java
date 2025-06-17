package com.ecommerce.converter;


import com.ecommerce.dto.response.AuthResponse;
import com.ecommerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    public AuthResponse toResponse(User user, String message, String token) {
        return new AuthResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                message, token);
    }
}
