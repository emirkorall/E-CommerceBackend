package com.ecommerce.order;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.user.User;
import java.util.List;

public interface OrderService {

  List<OrderResponse> findAllOrders(User user);

  OrderResponse findOrderById(long id, User user);

  OrderResponse saveOrder(OrderRequest request, User user);

  OrderResponse updateOrder(long id, OrderRequest request, User user);

  OrderResponse deleteOrderById(long id, User user);
}
