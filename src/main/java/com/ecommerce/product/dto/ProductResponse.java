package com.ecommerce.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
    Long id,
    String name,
    BigDecimal price,
    String image,
    String category,
    Double rating,
    Boolean isNew,
    List<String> features) {}
