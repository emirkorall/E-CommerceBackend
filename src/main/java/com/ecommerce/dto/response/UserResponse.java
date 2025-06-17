package com.ecommerce.dto.response;

public record UserResponse(Long id, String name, String surname, String email, com.ecommerce.entity.Role role) {
}
