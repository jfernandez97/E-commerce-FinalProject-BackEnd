package com.ecommerce.ecommercefinal.repository;

import com.ecommerce.ecommercefinal.model.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument,String> {
    public ProductDocument findByCode(String code);
}
