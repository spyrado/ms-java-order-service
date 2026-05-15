package com.ecommerce.order_service.dto.order.request;

import com.ecommerce.order_service.domain.enums.OrderStatus;

public record UpdateOrderRequestDTO(
    OrderStatus status
) {
}
