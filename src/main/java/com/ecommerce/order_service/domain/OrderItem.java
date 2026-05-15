package com.ecommerce.order_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Setter
  private String productId;

  @Column(nullable = false)
  @Setter
  private Integer quantity;

  @Column(nullable = false)
  @Setter
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  @Setter
  private Order order;
}
