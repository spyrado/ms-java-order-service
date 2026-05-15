package com.ecommerce.order_service.service.order;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.domain.OrderItem;
import com.ecommerce.order_service.domain.enums.OrderStatus;
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
    Order order = new Order();
    order.setCustomerId(request.customerId());
    order.setStatus(OrderStatus.PENDING);

    List<OrderItem> items = request.items()
        .stream()
        .map(itemRequest -> {
          OrderItem item = new OrderItem();
          item.setProductId(itemRequest.productId());
          item.setQuantity(itemRequest.quantity());
          item.setPrice(itemRequest.price());
          item.setOrder(order);

          return item;
        }).toList();

    order.setItems(items);

    Order saved = this.orderRepository.save(order);
    return new OrderResponseDTO(
        saved.getId(),
        saved.getCustomerId(),
        saved.getStatus(),
        saved.getCreatedAt()
    );
  }

  @Override
  public OrderResponseDTO update(UUID id, UpdateOrderRequestDTO request) {
    Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new OrderNotFoundException(id));

    order.setStatus(request.status());

    Order saved = this.orderRepository.save(order);


    return new OrderResponseDTO(
        saved.getId(),
        saved.getCustomerId(),
        saved.getStatus(),
        saved.getCreatedAt()
    );
  }

  @Override
  public void delete(UUID id) {
    Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new OrderNotFoundException(id));
    this.orderRepository.delete(order);
  }
}
