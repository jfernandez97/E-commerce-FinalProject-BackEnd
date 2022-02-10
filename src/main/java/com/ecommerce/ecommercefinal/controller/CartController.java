package com.ecommerce.ecommercefinal.controller;


import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.ecommerce.ecommercefinal.model.request.CartRequest;
import com.ecommerce.ecommercefinal.model.response.CartResponse;
import com.ecommerce.ecommercefinal.service.CartService;
import com.ecommerce.ecommercefinal.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final EmailService emailService;

    @PostMapping
    public CartResponse createCart(@RequestBody CartRequest cartRequest) throws ApiRestException{
        return cartService.createCart(cartRequest);
    }
    @GetMapping
    public List<CartResponse> getAllCarts(){
        return cartService.getAllCarts();
    }
    @DeleteMapping("/order/{orderNumber}")
    public void deleteCart(@PathVariable Integer orderNumber)throws ApiRestException{
        cartService.deleteCart(orderNumber);
    }
    @PostMapping("/add/{orderNumber}")
    public CartResponse addProduct(@PathVariable Integer orderNumber,@RequestBody CartItem item) throws ApiRestException{
        return cartService.addItem(orderNumber,item);
    }

    @GetMapping("/{orderNumber}")
    public List<CartItem> getAllItems(@PathVariable Integer orderNumber) throws ApiRestException{
        return cartService.getAllItems(orderNumber);
    }
    @PutMapping("/{orderNumber}")
    public CartResponse updateCartItem(@PathVariable Integer orderNumber,@RequestBody CartItem cartItem)throws ApiRestException{
        return cartService.updateCartItem(orderNumber,cartItem);
    }

    @DeleteMapping("/{orderNumber}")
    public void deleteCartItem(@PathVariable Integer orderNumber,@RequestParam String code) throws ApiRestException{
        cartService.deleteCartItem(orderNumber,code);
    }
    @GetMapping("/order/{orderNumber}")
    public List<CartItem> generateOrder(@PathVariable Integer orderNumber) throws ApiRestException, MessagingException {
        emailService.sendMailWithOrder(cartService.generateOrder(orderNumber));
        return cartService.generateOrder(orderNumber);
    }
}
