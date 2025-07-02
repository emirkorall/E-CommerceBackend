package com.ecommerce.order.dto;

import java.math.BigDecimal;

public record OrderResponse(
    Long id, Long addressId, String orderDate, String maskedCardNo, BigDecimal price) {}
