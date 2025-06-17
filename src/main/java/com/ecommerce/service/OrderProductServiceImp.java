package com.ecommerce.service;


import com.ecommerce.entity.OrderProduct;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.OrderProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductServiceImp implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImp(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public List<OrderProduct> findAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProduct findOrderProductById(Long id) {
        return orderProductRepository.findById(id)
                .orElseThrow(() -> new ApiException("Order product not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        if (orderProduct == null) {
            throw new ApiException("Order product data must not be null", HttpStatus.BAD_REQUEST);
        }
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public OrderProduct deleteOrderProductById(Long id) {
        OrderProduct orderProduct = findOrderProductById(id);
        if (orderProduct != null) {
            orderProductRepository.delete(orderProduct);
        }
        return orderProduct;
    }

    @Override
    public List<OrderProduct> findOrderProductsByOrderId(Long orderId) {
        return orderProductRepository.findByOrderId(orderId);
        
    }

    @Override
    public List<OrderProduct> findOrderProductsByProductId(Long productId) {
        return orderProductRepository.findByProductId(productId);
    }
}