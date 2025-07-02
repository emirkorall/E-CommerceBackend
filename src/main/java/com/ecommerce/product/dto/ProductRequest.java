package com.ecommerce.product.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
    @NotBlank(message = "Product name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,
    @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        BigDecimal price,
    @NotBlank(message = "Image URL is required") String image,
    @NotBlank(message = "Category is required") String category,
    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
        @DecimalMax(value = "5.0", message = "Rating cannot be greater than 5")
        Double rating,
    @Min(value = 0, message = "Review count cannot be negative") int reviews,
    Boolean isNew,
    List<String> features) {}
