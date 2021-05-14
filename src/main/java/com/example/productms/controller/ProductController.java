package com.example.productms.controller;

import com.example.productms.config.ErroRetorno;
import com.example.productms.model.Product;
import com.example.productms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    @Transactional
    public ResponseEntity<?> postProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveProduct(product));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> putProduct(@Valid @PathVariable String id ,@Valid @RequestBody Product product) {
      Product productDB  = service.putProduct(id, product);
        if(productDB != null){
            return ResponseEntity.ok(productDB);
        } else {
            ErroRetorno erro = new ErroRetorno(HttpStatus.BAD_REQUEST.value(), "requisição inválida");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsById(@Valid @PathVariable String id){
          Optional<Product> product  = service.getProductByID(id);
          if(product.isPresent()){
              return ResponseEntity.ok(product.get());
          } else {
              ErroRetorno erro = new ErroRetorno(HttpStatus.NOT_FOUND.value(), "recurso não foi localizado");
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
          }
    }

    @GetMapping
    public ResponseEntity<?> getProductsALL() {
        return ResponseEntity.ok(this.service.getProductAll());
    }
    @GetMapping("/search")
    public ResponseEntity<?> getProductsSearch(@RequestParam Optional<String> q ,@RequestParam Optional<String> min_price,@RequestParam Optional<String> max_price ) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.service.getProductBySearch(q, min_price, max_price));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProducts(@Valid @PathVariable String id) {
        this.service.deleteProductById(id);
        return ResponseEntity.ok().body("");
    }

}
