package com.ecommerce.converter;

import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setImage(request.image());
        product.setCategory(request.category());
        product.setRating(request.rating());
        product.setReviews(request.reviews());
        product.setNew(request.isNew());
        product.setFeatures(request.features());


        return product;


    }

    public ProductResponse toResponse(Product entity) {
        return new ProductResponse(entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getImage(),
                entity.getCategory(),
                entity.getRating(),
                entity.isNew(),
                entity.getFeatures());

    }
}
