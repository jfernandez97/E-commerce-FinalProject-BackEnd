package com.ecommerce.ecommercefinal.repository;

import com.ecommerce.ecommercefinal.model.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument,String> {
    public ProductDocument findByCode(String code);
    public List<ProductDocument> findByCategory(String category);
}
