package com.example.productms.repository;

import com.example.productms.model.Product;

import java.util.List;

public interface ProductRepositoryInter {

    List<Product> getProductBySearch(String q, String min_price, String max_price);

}
