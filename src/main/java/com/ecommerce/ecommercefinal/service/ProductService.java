package com.ecommerce.ecommercefinal.service;

import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.request.ProductRequest;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request) throws ApiRestException;
    ProductResponse update(String code,ProductRequest request) throws ApiRestException;
    List<ProductResponse> getAll();
    ProductResponse getByCode(String code) throws ApiRestException;
    void delete(String code)throws ApiRestException;

}
