package com.ecommerce.order;


import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public OrderServiceImp(OrderRepository orderRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findOrderById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("Order not found with id: " + id, HttpStatus.NOT_FOUND));
        return orderConverter.toResponse(order);
    }

    @Override
    public OrderResponse saveOrder(OrderRequest request) {
        if (request == null) {
            throw new ApiException("Order data must not be null", HttpStatus.BAD_REQUEST);
        }
        Order order = orderConverter.toEntity(request);
        Order savedOrder = orderRepository.save(order);
        return orderConverter.toResponse(savedOrder);
    }

    @Override

    public OrderResponse updateOrder(long id, OrderRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("Order not found with id: " + id, HttpStatus.NOT_FOUND));
        Order updated = orderConverter.toEntity(request);
        updated.setId(id);
        return orderConverter.toResponse(orderRepository.save(updated));
    }

    @Override
    public OrderResponse deleteOrderById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApiException("Order not found with id: " + id, HttpStatus.NOT_FOUND));
        orderRepository.delete(order);
        return orderConverter.toResponse(order);
    }
}
