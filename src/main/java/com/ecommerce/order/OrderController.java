package com.ecommerce.order;


import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService, OrderConverter orderConverter) {
        this.orderService = orderService;
    }

    @GetMapping

    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{id}")

    public ResponseEntity<OrderResponse> getOrderById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @PostMapping

    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.saveOrder(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")

    public ResponseEntity<OrderResponse> updateOrder(@PathVariable long id, @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable long id) {
        return ResponseEntity.ok(orderService.deleteOrderById(id));
    }

}
