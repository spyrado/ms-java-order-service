package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.dto.order.request.CreateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.request.UpdateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.response.OrderResponseDTO;
import com.ecommerce.order_service.dto.order.response.OrderSummaryResponseDTO;
import com.ecommerce.order_service.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderSummaryResponseDTO>> getAll() {
    return ResponseEntity.ok(this.orderService.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<OrderResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(this.orderService.findById(id));
  }

  @PostMapping()
  public ResponseEntity<OrderResponseDTO> create(@RequestBody CreateOrderRequestDTO request) {
    return ResponseEntity.status(201).body(this.orderService.create(request));
  }

  @PatchMapping("{id}")
  public ResponseEntity<OrderResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateOrderRequestDTO request) {
    return ResponseEntity.ok(this.orderService.update(id, request));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    this.orderService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
