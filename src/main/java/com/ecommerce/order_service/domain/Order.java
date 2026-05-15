package com.ecommerce.order_service.domain;

import com.ecommerce.order_service.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Setter
  private String customerId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Setter
  private OrderStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private ZonedDateTime createdAt;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  @Setter
  private List<OrderItem> items = new ArrayList<>();
}
