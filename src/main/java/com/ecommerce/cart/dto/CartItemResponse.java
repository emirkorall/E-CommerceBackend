package com.ecommerce.cart.dto;

import java.math.BigDecimal;

public record CartItemResponse(
    Long id, Long productId, String productName, int quantity, BigDecimal price) {}
