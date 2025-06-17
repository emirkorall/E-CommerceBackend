package com.ecommerce.dto.response;

import com.ecommerce.entity.Role;

public record AuthResponse(Long id, String name, String email, Role role, String message, String token) {
}
