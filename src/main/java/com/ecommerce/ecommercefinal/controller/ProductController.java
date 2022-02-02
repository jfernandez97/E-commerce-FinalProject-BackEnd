package com.ecommerce.ecommercefinal.controller;

import com.ecommerce.ecommercefinal.model.request.ProductRequest;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;
import com.ecommerce.ecommercefinal.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;



    @PostMapping
    public  ProductResponse createProduct(@Validated @RequestBody ProductRequest request) throws Exception {
        return service.create(request);
    }

    @PutMapping("/{code}")
    public ProductResponse update(@PathVariable String code,@RequestBody ProductRequest request) throws Exception {
        return service.update(code,request);
    }

    @GetMapping("/{code}")
    public ProductResponse getByCode(@PathVariable String code){
        return service.getByCode(code);
    }

    @GetMapping
    public List<ProductResponse> getAll(){
        return service.getAll();
    }
}
