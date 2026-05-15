package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.dto.order.request.CreateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.request.UpdateOrderRequestDTO;
import com.ecommerce.order_service.dto.order.response.OrderResponseDTO;
import com.ecommerce.order_service.dto.order.response.OrderSummaryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @GetMapping
  public List<OrderSummaryResponseDTO> getAll() {
    System.out.println("CHAMEI O ENDPOINT DE LISTA");
    return List.of();
  }

  @GetMapping("{id}")
  public Optional<OrderResponseDTO> getById(@PathVariable UUID id) {
    System.out.println("RECEBI O ID:" + id);
    return Optional.empty();
  }

  @PostMapping()
  public ResponseEntity<OrderResponseDTO> create(@RequestBody CreateOrderRequestDTO input) {
    System.out.println("RECEBI ISSO NO POST: " + input);
    return ResponseEntity.status(201).build();
  }

  @PutMapping("{id}")
  public ResponseEntity<OrderResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateOrderRequestDTO input) {
    System.out.println("RECEBI ISSO NO PUT: " + input);
    return ResponseEntity.status(200).build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    return ResponseEntity.noContent().build();
  }
}
