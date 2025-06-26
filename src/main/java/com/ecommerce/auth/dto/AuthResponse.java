package com.ecommerce.auth.dto;

public record AuthResponse(
    Long id, String name, String email, String authority, String message, String token) {}
