package com.ecommerce.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(
    @NotBlank(message = "Title is required") String title,
    @NotBlank(message = "Name is required") String name,
    @NotBlank(message = "Surname is required") String surname,
    @Pattern(regexp = "\\+?[0-9\\- ]{7,15}", message = "Invalid phone number") String phone,
    @NotBlank(message = "City is required") String city,
    @NotBlank(message = "District is required") String district,
    @NotBlank(message = "Neighborhood is required") String neighborhood) {}
