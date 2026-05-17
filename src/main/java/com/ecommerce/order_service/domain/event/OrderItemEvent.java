package com.ecommerce.order_service.domain.event;

import java.math.BigDecimal;

public record OrderItemEvent(
    String productId,
    Integer quantity,
    BigDecimal price
) {}