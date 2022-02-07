package com.ecommerce.ecommercefinal.service;

import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.request.UserRequest;
import com.ecommerce.ecommercefinal.model.response.UserResponse;

public interface UserService {
    UserResponse login(String email,String password) throws ApiRestException;
    UserResponse register(UserRequest request)throws ApiRestException;
}
