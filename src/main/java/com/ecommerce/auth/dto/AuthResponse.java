package com.ecommerce.auth.dto;

import com.ecommerce.role.Role;

public record AuthResponse(Long id, String name, String email, Role role, String message, String token) {
}
