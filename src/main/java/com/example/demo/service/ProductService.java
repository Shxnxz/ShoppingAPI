package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. Get Single Product
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 3. Create Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 4. Update Product
    public Product updateProduct(Long id, Product details) {
        return productRepository.findById(id).map(product -> {
            product.setName(details.getName());
            product.setDescription(details.getDescription());
            product.setPrice(details.getPrice());
            product.setStockQuantity(details.getStockQuantity());
            product.setCategory(details.getCategory());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // 5. Delete Product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // 6. BUY LOGIC
    @Transactional 
    public Product buyProduct(Long id, int amount) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < amount) {
            throw new IllegalArgumentException("Out of Stock! Only " + product.getStockQuantity() + " left.");
        }

        product.setStockQuantity(product.getStockQuantity() - amount);
        return productRepository.save(product);
    }
}