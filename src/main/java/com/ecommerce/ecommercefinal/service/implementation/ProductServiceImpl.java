package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.builder.ProductBuilder;
import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.ProductDocument;
import com.ecommerce.ecommercefinal.model.request.ProductRequest;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;
import com.ecommerce.ecommercefinal.repository.ProductRepository;
import com.ecommerce.ecommercefinal.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductResponse create(ProductRequest request) throws ApiRestException {
        validateRequestCreate(request);
        var document = repository.save(ProductBuilder.requestToDocumentCreate(request));
        return ProductBuilder.documentToResponse(document);
    }

    @Override
    public ProductResponse update(String code, ProductRequest request) throws ApiRestException {
        validateRequestExists(code);
        var product = repository.findByCode(code);
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setModificationDate(LocalDateTime.now());
        var productSaved = repository.save(product);
        return ProductBuilder.documentToResponseUpdate(productSaved);
    }

    @Override
    public List<ProductResponse> getAll() {
        return ProductBuilder.listDocumentToResponse(repository.findAll());
    }

    @Override
    public ProductResponse getByCode(String code) throws ApiRestException {
        validateRequestExists(code);
        return ProductBuilder.documentToResponse(repository.findByCode(code));
    }

    @Override
    public List<ProductResponse> getByCategory(String category) throws ApiRestException {
        if(repository.findByCategory(category).isEmpty()){
            throw new ApiRestException(category,"Error, la categoria que intenta buscar no existe");
        }
        return ProductBuilder.listDocumentToResponse(repository.findByCategory(category));
    }

    @Override
    public void delete(String code) throws ApiRestException {
        validateRequestExists(code);
        var product =repository.findByCode(code);
        repository.delete(product);
    }

    private void validateRequestCreate(ProductRequest request) throws ApiRestException {
        var product = repository.findByCode(request.getCode());
        if(!Objects.isNull(product)){
           throw new ApiRestException(request.getCode(),"Error, el producto ya existe");
        }
    }
    private void validateRequestExists(String code) throws ApiRestException {
        var product = repository.findByCode(code);
        if(Objects.isNull(product)){
            throw new ApiRestException(code,"Error,el producto no existe");
        }
    }
}
