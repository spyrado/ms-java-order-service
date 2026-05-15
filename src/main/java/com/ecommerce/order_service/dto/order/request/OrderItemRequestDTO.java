package com.ecommerce.order_service.dto.order.request;

import java.math.BigDecimal;

public record OrderItemRequestDTO(
    String productId,
    Integer quantity,
    BigDecimal price
) {
}
