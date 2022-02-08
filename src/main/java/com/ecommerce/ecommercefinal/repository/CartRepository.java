package com.ecommerce.ecommercefinal.repository;

import com.ecommerce.ecommercefinal.model.document.CartDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<CartDocument,Integer> {
    public CartDocument findByEmail(String email);
    public CartDocument findByOrderNumber (Integer orderNumber);
}
