package com.ecommerce.ecommercefinal.builder;


import com.ecommerce.ecommercefinal.model.document.ProductDocument;
import com.ecommerce.ecommercefinal.model.request.ProductRequest;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    public static ProductDocument requestToDocumentCreate(ProductRequest request){
        return ProductDocument.builder()
                .code(request.getCode())
                .category(request.getCategory())
                .price(request.getPrice())
                .description(request.getDescription())
                .creationDate(LocalDateTime.now())
                .build();
    }
    public static ProductResponse documentToResponseCreate(ProductDocument document){
        return ProductResponse.builder()
                .code(document.getCode())
                .creationTime(document.getCreationDate())
                .modificationDate(document.getModificationDate())
                .build();
    }

    public static ProductResponse documentToResponseUpdate(ProductDocument document){
        return ProductResponse.builder()
                .code(document.getCode())
                .creationTime(document.getCreationDate())
                .modificationDate(document.getModificationDate())
                .build();
    }
    public static  ProductResponse documentToResponse(ProductDocument document){
        return ProductResponse.builder()
                .code(document.getCode())
                .category(document.getCategory())
                .price(document.getPrice())
                .description(document.getDescription())
                .creationTime(document.getCreationDate())
                .modificationDate(document.getModificationDate())
                .build();
    }
    public static List<ProductResponse> listDocumentToResponse(List<ProductDocument> documents){
        var listResponse =  new ArrayList<ProductResponse>();
        documents.forEach(doc ->listResponse.add(documentToResponse(doc)));
        return listResponse;
    }

}
