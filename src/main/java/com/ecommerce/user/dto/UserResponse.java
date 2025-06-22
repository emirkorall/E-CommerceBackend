package com.ecommerce.user.dto;

import com.ecommerce.role.Role;

public record UserResponse(Long id, String name, String surname, String email, Role role) {
}
