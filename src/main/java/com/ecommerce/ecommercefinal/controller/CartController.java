package com.ecommerce.ecommercefinal.controller;


import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.ecommerce.ecommercefinal.model.request.CartRequest;
import com.ecommerce.ecommercefinal.model.response.CartResponse;
import com.ecommerce.ecommercefinal.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartResponse create(@RequestBody CartRequest cartRequest) throws ApiRestException{
        return cartService.create(cartRequest);
    }
    @GetMapping
    public List<CartResponse> getAll(){
        return cartService.getAll();
    }
    @PostMapping("/add/{orderNumber}")
    public CartResponse addProduct(@PathVariable Integer orderNumber,@RequestBody CartItem item) throws ApiRestException{
        return cartService.addProduct(orderNumber,item);
    }
    @GetMapping("/{orderNumber}")
    public List<CartItem> getAllItems(@PathVariable Integer orderNumber) throws ApiRestException{
        return cartService.getAllItems(orderNumber);
    }
}
