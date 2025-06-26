package com.ecommerce.order;

import com.ecommerce.exception.ApiException;
import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;

  public OrderServiceImp(OrderRepository orderRepository, OrderConverter orderConverter) {
    this.orderRepository = orderRepository;
    this.orderConverter = orderConverter;
  }

  @Override
  public List<OrderResponse> findAllOrders(User user) {
    return orderRepository.findAllByUser(user).stream()
        .map(orderConverter::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public OrderResponse findOrderById(long id, User user) {
    Order order =
        orderRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Order not found", HttpStatus.NOT_FOUND));
    return orderConverter.toResponse(order);
  }

  @Override
  public OrderResponse saveOrder(OrderRequest request, User user) {
    if (request == null) {
      throw new ApiException("Order data must not be null", HttpStatus.BAD_REQUEST);
    }
    Order order = orderConverter.toEntity(request);
    order.setUser(user);
    Order savedOrder = orderRepository.save(order);
    return orderConverter.toResponse(savedOrder);
  }

  @Override
  public OrderResponse updateOrder(long id, OrderRequest request, User user) {
    Order existingOrder =
        orderRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Order not found", HttpStatus.NOT_FOUND));
    orderConverter.updateEntity(existingOrder, request);
    return orderConverter.toResponse(orderRepository.save(existingOrder));
  }

  @Override
  public OrderResponse deleteOrderById(long id, User user) {
    Order order =
        orderRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Order not found", HttpStatus.NOT_FOUND));
    orderRepository.delete(order);
    return orderConverter.toResponse(order);
  }
}
