package com.ll.mutbooks.product.repository;

import com.ll.mutbooks.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
