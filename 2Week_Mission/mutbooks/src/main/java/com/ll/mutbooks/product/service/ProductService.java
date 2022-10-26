package com.ll.mutbooks.product.service;

import com.ll.mutbooks.product.entity.Product;
import com.ll.mutbooks.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
