package com.ecommerce.order.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record OrderRequest(
    @Min(value = 1, message = "Address ID must be a valid ID") Long addressId,
    @NotBlank(message = "Order date is required") String orderDate,
    @NotBlank(message = "Card number is required")
        @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
        String cardNo,
    @NotBlank(message = "Card holdder name is required")
        @Size(min = 2, max = 50, message = "Card name must be between 2 and 50 characters")
        String cardName,
    @NotBlank(message = "Card month is required")
        @Pattern(regexp = "0[1-9]|1[0-2]", message = "Month must be between 01 and 12")
        String cardMonth,
    @NotBlank(message = "Card year is required")
        @Pattern(regexp = "\\\\d{2}", message = "Year must be 2 digits")
        String cardYear,
    @NotBlank(message = "CVV is required")
        @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
        String cardCvv,
    @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price) {}
