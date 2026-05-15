package com.ecommerce.order_service.service.order;

import com.ecommerce.order_service.dto.order.request.CreateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.request.UpdateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.response.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
  @Override
  public List<OrderResponseDTO> findAll() {
    return List.of();
  }

  @Override
  public OrderResponseDTO findById(UUID id) {
    return null;
  }

  @Override
  public OrderResponseDTO create(CreateOrderRequestDTO request) {
    return null;
  }

  @Override
  public OrderResponseDTO update(UUID id, UpdateOrderRequestDTO request) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
