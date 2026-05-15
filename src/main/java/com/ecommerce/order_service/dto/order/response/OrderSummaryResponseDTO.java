package com.ecommerce.order_service.dto.order.response;

import com.ecommerce.order_service.domain.enums.OrderStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public record OrderSummaryResponseDTO(
    UUID id,
    String customerId,
    OrderStatus status,
    ZonedDateTime createdAt
    ) {
}
