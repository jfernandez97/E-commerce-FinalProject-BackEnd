package com.ecommerce.ecommercefinal.builder;


import com.ecommerce.ecommercefinal.model.document.ProductDocument;
import com.ecommerce.ecommercefinal.model.request.ProductRequest;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    public static ProductDocument requestToDocumentCreate(ProductRequest productRequest){
        return ProductDocument.builder()
                .code(productRequest.getCode())
                .category(productRequest.getCategory())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .creationDate(LocalDateTime.now())
                .build();
    }
//    public static ProductResponse documentToResponseCreate(ProductDocument productDocument){
//        return ProductResponse.builder()
//                .code(productDocument.getCode())
//                .creationTime(productDocument.getCreationDate())
//                .modificationDate(productDocument.getModificationDate())
//                .build();
//    }

    public static ProductResponse documentToResponseUpdate(ProductDocument productDocument){
        return ProductResponse.builder()
                .code(productDocument.getCode())
                .creationTime(productDocument.getCreationDate())
                .modificationDate(productDocument.getModificationDate())
                .build();
    }
    public static  ProductResponse documentToResponse(ProductDocument productDocument){
        return ProductResponse.builder()
                .code(productDocument.getCode())
                .category(productDocument.getCategory())
                .price(productDocument.getPrice())
                .description(productDocument.getDescription())
                .creationTime(productDocument.getCreationDate())
                .modificationDate(productDocument.getModificationDate())
                .build();
    }
    public static List<ProductResponse> listDocumentToResponse(List<ProductDocument> productDocuments){
        var listResponse =  new ArrayList<ProductResponse>();
        productDocuments.forEach(doc ->listResponse.add(documentToResponse(doc)));
        return listResponse;
    }

}
