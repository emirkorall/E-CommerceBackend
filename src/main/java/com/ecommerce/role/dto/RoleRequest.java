package com.ecommerce.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequest(@NotBlank @Size(min = 2, max = 50) String authority) {}
