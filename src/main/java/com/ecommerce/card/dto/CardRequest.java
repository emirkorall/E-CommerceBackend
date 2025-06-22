package com.ecommerce.card.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CardRequest(
        @NotBlank
        @Pattern(regexp = "\\d{4}", message = "Card number must be last 4 digits") String cardNo,
        @NotBlank
        @Pattern(regexp = "\\d{16}", message = "Card number must be exactly 16 digits") String cardName,
        @NotBlank
        @Pattern(regexp = "0[1-9]|1[0-2]", message = "Card month must be between 01 and 12") String cardMonth,
        @NotBlank
        @Pattern(regexp = "\\d{2}", message = "Card year must be 2 digits")
        @Column(name = "card_year") String cardYear,
        @NotBlank
        @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits") String cardCvv) {
}
