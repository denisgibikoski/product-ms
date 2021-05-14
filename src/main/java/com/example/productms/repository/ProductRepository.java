package com.example.productms.repository;

import com.example.productms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameOrDescriptionOrPriceGreaterThanEqualOrPriceLessThanEqual(String q, String q1, Double min_price, Double max_price);
}
