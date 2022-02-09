package com.ecommerce.ecommercefinal.service;

import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.ecommerce.ecommercefinal.model.request.CartRequest;
import com.ecommerce.ecommercefinal.model.response.CartResponse;

import java.util.List;

public interface CartService {
    public CartResponse createCart(CartRequest cartRequest) throws ApiRestException;
    public List<CartResponse> getAllCarts();
    public CartResponse updateCartItem(Integer orderNumber, CartItem cartItem) throws ApiRestException;
    public void deleteCart(Integer orderNumber) throws ApiRestException; //se eleminara producto con determiando code
    public void deleteCartItem(Integer orderNumber, String code) throws ApiRestException;
    public CartResponse addItem(Integer orderNumber, CartItem cartItem) throws ApiRestException;// Se agrega producto por su code y su cantidad
    public List<CartItem> getAllItems(Integer orderNumber) throws ApiRestException;
    public List<CartItem> generateOrder (Integer orderNubmer) throws ApiRestException;
}
