package com.ecommerce.ecommercefinal.builder;

import com.ecommerce.ecommercefinal.model.document.CartDocument;
import com.ecommerce.ecommercefinal.model.request.CartRequest;
import com.ecommerce.ecommercefinal.model.response.CartResponse;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartBuilder {
    private static Integer ID=0;

    public static CartDocument requestToDocumentCreate(CartRequest cartRequest){
        return CartDocument.builder()
                .email(cartRequest.getEmail())
                .orderNumber(++ID)
                .products(new ArrayList<>())
                .createDate(LocalDateTime.now())
                .status("Generated")
                .deliverAddress(cartRequest.getDeliverAddress())
                .build();
    }
    public static CartResponse documentToResponse(CartDocument cartDocument){
        return CartResponse.builder()
                .orderNumber(cartDocument.getOrderNumber())
                .createDate(cartDocument.getCreateDate())
                .products(cartDocument.getProducts())
                .email(cartDocument.getEmail())
                .status(cartDocument.getStatus())
                .build();
    }
    public static List<CartResponse> listDocumentToResponse(List<CartDocument> cartDocuments){
        List<CartResponse> listResponse =  new ArrayList<>();
        cartDocuments.forEach(doc ->listResponse.add(documentToResponse(doc)));
        return listResponse;
    }


}
