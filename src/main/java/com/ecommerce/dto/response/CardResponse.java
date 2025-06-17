package com.ecommerce.dto.response;

public record CardResponse(Long id, String maskedCardNo, String cardName, String cardMonth, String cardYear) {
}

