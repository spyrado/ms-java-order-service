package com.ecommerce.order_service.dto.order.request;

import java.util.List;

public record CreateOrderRequestDTO(
    String customerId,
    List<OrderItemRequestDTO> items
) {
}
