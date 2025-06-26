package com.ecommerce.cart.dto;

import java.util.List;
import java.math.BigDecimal;

public record CartResponse(Long id, List<CartItemResponse> items, BigDecimal total) {} 