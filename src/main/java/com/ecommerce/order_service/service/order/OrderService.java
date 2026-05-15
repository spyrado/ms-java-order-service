package com.ecommerce.order_service.service.order;

import com.ecommerce.order_service.dto.order.request.CreateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.request.UpdateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.response.OrderResponseDTO;
import com.ecommerce.order_service.dto.order.response.OrderSummaryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {
  List<OrderSummaryResponseDTO> findAll();
  OrderResponseDTO findById(UUID id);
  OrderResponseDTO create(CreateOrderRequestDTO request);
  OrderResponseDTO update(UUID id, UpdateOrderRequestDTO request);
  void delete(UUID id);
}
