package com.ecommerce.ecommercefinal.service;

import com.ecommerce.ecommercefinal.model.request.ProductRequest;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductResponse create(ProductRequest request) throws Exception;
    public ProductResponse update(String code,ProductRequest request) throws Exception;
    public List<ProductResponse> getAll();
    public ProductResponse getByCode(String code);
}
