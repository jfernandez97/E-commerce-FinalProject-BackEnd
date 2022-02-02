package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.builder.ProductBuilder;
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
    public ProductResponse create(ProductRequest request) throws Exception {

        validateRequestCreate(request);
        var document = repository.save(ProductBuilder.requestToDocumentCreate(request));
        return ProductBuilder.documentToResponseCreate(document);
    }

    @Override
    public ProductResponse update(String code, ProductRequest request) throws Exception {
        validateRequestUpdate(code);
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
    public ProductResponse getByCode(String code) {
        return ProductBuilder.documentToResponse(repository.findByCode(code));
    }

    private void validateRequestCreate(ProductRequest request) throws Exception {
        var product = repository.findByCode(request.getCode());
        if(!Objects.isNull(product)){
           throw new Exception("El producto ya existe");
        }
    }
    private void validateRequestUpdate(String code) throws Exception {
        var product = repository.findByCode(code);
        if(Objects.isNull(product)){
            throw new Exception("El producto que intenta modificar no existe");
        }
    }
}
