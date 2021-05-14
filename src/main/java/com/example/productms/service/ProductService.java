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

    public List<Product> getProductBySearch(String q, Double min_price,Double max_price)  {
        String q1 = q ;
        if(min_price == null ){
            min_price = new Double(0.0);
        }
        if(max_price == null ){
            max_price = new Double(0.0);
        }
        List<Product>  products = repository.findAllByNameOrDescriptionOrPriceGreaterThanEqualOrPriceLessThanEqual(q,q1,min_price,max_price);
        return products;
    }


}
