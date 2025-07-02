package com.ecommerce.order;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.user.User;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService, OrderConverter orderConverter) {
    this.orderService = orderService;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> getAllOrders(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(orderService.findAllOrders(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrderById(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(orderService.findOrderById(id, user));
  }

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(
      @RequestBody OrderRequest request, @AuthenticationPrincipal User user) {
    return ResponseEntity.status(201).body(orderService.saveOrder(request, user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderResponse> updateOrder(
      @PathVariable long id,
      @RequestBody OrderRequest request,
      @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(orderService.updateOrder(id, request, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<OrderResponse> deleteOrder(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(orderService.deleteOrderById(id, user));
  }
}
