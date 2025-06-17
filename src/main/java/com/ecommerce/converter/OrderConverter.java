package com.ecommerce.converter;


import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    public Order toEntity(OrderRequest request) {
        Order order = new Order();
        order.setAddressId(request.addressId());
        order.setCardNo(request.cardNo());
        order.setCardName(request.cardName());
        order.setCardMonth(request.cardMonth());
        order.setCardYear(request.cardYear());
        order.setCardCvv(request.cardCvv());
        order.setPrice(request.price());
        return order;
    }

    public OrderResponse toResponse(Order order) {
        String maskedCard = "**** **** ****" + order.getCardNo().substring(12);
        return new OrderResponse(order.getId(),
                order.getAddressId(),
                order.getOrderDate(),
                maskedCard,
                order.getPrice());
    }
}
