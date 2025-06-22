package com.ecommerce.order;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(long id);

    OrderResponse saveOrder(OrderRequest request);

    OrderResponse updateOrder(long id, OrderRequest request);

    OrderResponse deleteOrderById(long id);
}
