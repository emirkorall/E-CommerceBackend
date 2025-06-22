package com.ecommerce.product;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(long id);

    ProductResponse saveProduct(ProductRequest request);

    ProductResponse updateProduct(long id, ProductRequest request);

    ProductResponse deleteProductById(long id);
}
