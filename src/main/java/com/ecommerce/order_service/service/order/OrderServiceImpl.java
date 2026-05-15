package com.ecommerce.order_service.service.order;

import com.ecommerce.order_service.dto.order.request.CreateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.request.UpdateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.response.OrderResponseDTO;
import com.ecommerce.order_service.dto.order.response.OrderSummaryResponseDTO;
import com.ecommerce.order_service.exception.OrderNotFoundException;
import com.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public List<OrderSummaryResponseDTO> findAll() {
    return this.orderRepository.findAll()
        .stream()
        .map(order -> new OrderSummaryResponseDTO(
            order.getId(),
            order.getCustomerId(),
            order.getStatus(),
            order.getCreatedAt()
        )).toList();
  }

  @Override
  public OrderResponseDTO findById(UUID id) {
    return this.orderRepository.findById(id)
        .map(order -> new OrderResponseDTO(
            order.getId(),
            order.getCustomerId(),
            order.getStatus(),
            order.getCreatedAt()
        )).orElseThrow(() -> new OrderNotFoundException(id));
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
