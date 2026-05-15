package com.ecommerce.order_service.exception.handlers;

import com.ecommerce.order_service.exception.OrderNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
@Order(1)
public class OrderExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(OrderExceptionHandler.class);

  @ExceptionHandler(OrderNotFoundException.class)
  public ProblemDetail handleOrderNotFound(OrderNotFoundException ex, HttpServletRequest request) {
    log.error("Pedido não encontrado: {}", ex.getMessage());
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problem.setTitle("Pedido não encontrado");
    problem.setInstance(URI.create(request.getRequestURI()));
    return problem;
  }
}