package com.ecommerce.service;

import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(long id);

    OrderResponse saveOrder(OrderRequest request);

    OrderResponse updateOrder(long id, OrderRequest request);

    OrderResponse deleteOrderById(long id);
}
