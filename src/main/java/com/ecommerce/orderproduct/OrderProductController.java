package com.ecommerce.orderproduct;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-products")
public class OrderProductController {

    public final OrderProductService orderProductService;

    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping
    public ResponseEntity<List<OrderProduct>> getAllOrderProducts() {
        List<OrderProduct> orderProducts = orderProductService.findAllOrderProducts();
        return ResponseEntity.ok(orderProducts);
    }

    @GetMapping("/{id}")

    public ResponseEntity<OrderProduct> getOrderProductById(@PathVariable long id) {
        OrderProduct orderProduct = orderProductService.findOrderProductById(id);
        if (orderProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderProduct);
    }

    @PostMapping

    public ResponseEntity<OrderProduct> createOrderProduct(@RequestBody OrderProduct orderProduct) {
        OrderProduct savedOrderProduct = orderProductService.saveOrderProduct(orderProduct);
        return ResponseEntity.status(201).body(savedOrderProduct);
    }

    @PutMapping("/{id}")

    public ResponseEntity<OrderProduct> updateOrderProduct(@RequestBody OrderProduct orderProduct) {
        OrderProduct existingOrderProduct = orderProductService.findOrderProductById(orderProduct.getId());
        existingOrderProduct.setQuantity(orderProduct.getQuantity());
        OrderProduct updatedOrderProduct = orderProductService.saveOrderProduct(existingOrderProduct);
        return ResponseEntity.ok(updatedOrderProduct);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<OrderProduct> deleteOrderProduct(@PathVariable long id) {
        OrderProduct deletedOrderProduct = orderProductService.deleteOrderProductById(id);
        if (deletedOrderProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedOrderProduct);
    }
}
