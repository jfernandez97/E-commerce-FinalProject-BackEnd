package com.ecommerce.ecommercefinal.builder;

import com.ecommerce.ecommercefinal.model.document.UserDocument;
import com.ecommerce.ecommercefinal.model.request.UserRequest;
import com.ecommerce.ecommercefinal.model.response.UserResponse;

public class UserBuilder {

    public  static UserDocument requestToDocument(UserRequest request){
        return UserDocument.builder()
                .username(request.getUsername())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .build();
    }
    public static UserResponse documentToResponse(UserDocument userDocument){
        return UserResponse.builder()
                .username(userDocument.getUsername())
                .email(userDocument.getEmail())
                .build();
    }
}
