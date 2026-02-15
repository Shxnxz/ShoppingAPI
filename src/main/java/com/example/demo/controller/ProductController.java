package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. GET ALL: List every item in the store
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 2. GET ONE: See details of a specific item
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. CREATE: Add new inventory
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // 4. UPDATE: Change price or description
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, productDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE: Remove an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 6. BUY ITEM
    @PostMapping("/{id}/buy")
    public ResponseEntity<?> buyProduct(@PathVariable Long id, @RequestParam int amount) {
        try {
            Product updatedProduct = productService.buyProduct(id, amount);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            // Returns HTTP 400 if stock is too low
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Returns HTTP 404 if product doesn't exist
            return ResponseEntity.notFound().build();
        }
    }
}