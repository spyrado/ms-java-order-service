package com.ecommerce.order_service.service.order;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.domain.OrderItem;
import com.ecommerce.order_service.domain.enums.OrderStatus;
import com.ecommerce.order_service.domain.event.OrderCreatedEvent;
import com.ecommerce.order_service.domain.event.OrderItemEvent;
import com.ecommerce.order_service.dto.order.request.CreateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.request.UpdateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.response.OrderResponseDTO;
import com.ecommerce.order_service.dto.order.response.OrderSummaryResponseDTO;
import com.ecommerce.order_service.exception.OrderNotFoundException;
import com.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
  private final OrderRepository orderRepository;
  private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

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
        .map(this::orderResponseDTO).orElseThrow(() -> new OrderNotFoundException(id));
  }

  @Override
  public OrderResponseDTO create(CreateOrderRequestDTO request) {
    Order saved = this.orderRepository.save(this.buildOrder(request));
    this.dispatchEventOrderCreatedToKafka(saved);
    return this.orderResponseDTO(saved);
  }

  private void dispatchEventOrderCreatedToKafka(Order order) {
    BigDecimal total = order.getItems().stream()
        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    OrderCreatedEvent event = new OrderCreatedEvent(
        order.getId(),
        order.getCustomerId(),
        order.getItems().stream().map(item ->
            new OrderItemEvent(
                item.getProductId(),
                item.getQuantity(),
                item.getPrice()
            )).toList(),
        total
    );

    try {
      kafkaTemplate.send("order-created", order.getId().toString(), event);
      log.info("Evento publicado no Kafka para o pedido: {}", order.getId());
    } catch (Exception e) {
      log.error("Erro ao publicar evento no Kafka: {}", e.getMessage());
    }
  }

  private Order buildOrder(CreateOrderRequestDTO request) {
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

    return order;
  }

  @Override
  public OrderResponseDTO update(UUID id, UpdateOrderRequestDTO request) {
    Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new OrderNotFoundException(id));

    order.setStatus(request.status());

    Order saved = this.orderRepository.save(order);


    return this.orderResponseDTO(saved);
  }

  @Override
  public void delete(UUID id) {
    Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new OrderNotFoundException(id));
    this.orderRepository.delete(order);
  }

  private OrderResponseDTO orderResponseDTO(Order order) {
    return new OrderResponseDTO(
        order.getId(),
        order.getCustomerId(),
        order.getStatus(),
        order.getCreatedAt()
    );
  }
}
