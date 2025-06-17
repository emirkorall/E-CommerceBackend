package com.ecommerce.service;

import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(long id);

    ProductResponse saveProduct(ProductRequest request);

    ProductResponse updateProduct(long id, ProductRequest request);

    ProductResponse deleteProductById(long id);
}
