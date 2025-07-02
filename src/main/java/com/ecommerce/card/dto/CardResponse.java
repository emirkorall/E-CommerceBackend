package com.ecommerce.card.dto;

public record CardResponse(
    Long id, String maskedCardNo, String cardName, String cardMonth, String cardYear) {}
