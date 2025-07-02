package com.ecommerce.cart.dto;

import java.util.List;

public record CartRequest(List<CartItemRequest> items) {}
