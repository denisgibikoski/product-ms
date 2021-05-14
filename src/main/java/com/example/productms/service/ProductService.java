package com.example.productms.service;

import com.example.productms.model.Product;
import com.example.productms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  ProductService {

    @Autowired
    ProductRepository repository;

    public Product saveProduct(Product product) {
        return  repository.save(product);
    }

    public Product putProduct( String id, Product product)  {
        Optional<Product> productBD = repository.findById(Long.valueOf(id));
        Product productSave = new Product();
        productSave.setId(productBD.get().getId());
        productSave.setDescription(product.getDescription());
        productSave.setName(product.getName());
        productSave.setPrice(product.getPrice());
        productSave = repository.saveAndFlush(productSave);
        return productSave;
    }

    public Optional<Product> getProductByID(String id)  {
        return repository.findById(Long.valueOf(id));
    }

    public List<Product> getProductAll() {
        return repository.findAll();
    }

    public void deleteProductById(String id) {
       repository.deleteById(Long.valueOf(id));
    }

    public List<Product> getProductBySearch(Optional<String> q, Optional<String> min_price, Optional<String> max_price) {
        List<Product> products = new ArrayList<>();

        products = repository.getProductBySearch(q.get(),min_price.get(),max_price.get());

        return products;
    }
}
