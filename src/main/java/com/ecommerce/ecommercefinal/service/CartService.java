package com.ecommerce.ecommercefinal.service;

import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.ecommerce.ecommercefinal.model.request.CartRequest;
import com.ecommerce.ecommercefinal.model.response.CartResponse;
import com.ecommerce.ecommercefinal.model.response.ProductResponse;

import java.util.List;

public interface CartService {
    public CartResponse create(CartRequest cartRequest) throws ApiRestException;
    public List<CartResponse> getAll();
    public CartResponse update(Integer orderNumber, CartItem cartItem);
    public void delete(Integer orderNumber); //se eleminara producto con determiando code
    public CartResponse addProduct(Integer orderNumber, CartItem cartItem) throws ApiRestException;// Se agrega producto por su code y su cantidad
    public List<CartItem> getAllItems(Integer orderNumber) throws ApiRestException;
}
