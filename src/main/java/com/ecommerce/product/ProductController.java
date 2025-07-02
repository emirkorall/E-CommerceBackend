package com.ecommerce.product;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  public final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.ok(productService.findAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable long id) {
    return ResponseEntity.ok(productService.findProductById(id));
  }

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
    return ResponseEntity.status(201).body(productService.saveProduct(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(
      @PathVariable Long id, @RequestBody ProductRequest request) {
    ProductResponse response = productService.updateProduct(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponse> deleteProduct(@PathVariable long id) {
    return ResponseEntity.ok(productService.deleteProductById(id));
  }
}
