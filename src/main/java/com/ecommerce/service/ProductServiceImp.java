package com.ecommerce.service;


import com.ecommerce.converter.ProductConverter;
import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductServiceImp(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        return productConverter.toResponse(product);
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        if (request == null) {
            throw new ApiException("Product data must not be null", HttpStatus.BAD_REQUEST);
        }
        Product product = productConverter.toEntity(request);
        return productConverter.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(long id, ProductRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        Product updated = productConverter.toEntity(request);
        updated.setId(id);
        return productConverter.toResponse(productRepository.save(updated));
    }

    @Override
    public ProductResponse deleteProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        productRepository.delete(product);
        return productConverter.toResponse(product);
    }
}
