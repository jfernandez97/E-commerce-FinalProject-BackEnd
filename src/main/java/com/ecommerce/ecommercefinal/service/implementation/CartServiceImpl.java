package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.builder.CartBuilder;
import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.CartDocument;
import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.ecommerce.ecommercefinal.model.request.CartRequest;
import com.ecommerce.ecommercefinal.model.response.CartResponse;
import com.ecommerce.ecommercefinal.repository.CartRepository;
import com.ecommerce.ecommercefinal.repository.ProductRepository;
import com.ecommerce.ecommercefinal.repository.UserRepository;
import com.ecommerce.ecommercefinal.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CartResponse createCart(CartRequest cartRequest) throws ApiRestException {
        validateCartCreate(cartRequest);
        CartDocument cartDocumentFound = cartRepository.save(CartBuilder.requestToDocumentCreate(cartRequest));
        return CartBuilder.documentToResponse(cartDocumentFound);
    }

    @Override
    public List<CartResponse> getAllCarts() {
        return CartBuilder.listDocumentToResponse(cartRepository.findAll());
    }


    @Override
    public void deleteCart(Integer orderNumber) throws ApiRestException {
        validateCartExists(orderNumber);
        var cartFound = cartRepository.findByOrderNumber(orderNumber);
        cartRepository.delete(cartFound);

    }

    @Override
    public CartResponse addItem(Integer orderNumber, CartItem cartItem) throws ApiRestException {

        validateCartExists(orderNumber);
        validateProductExists(cartItem.getCode());
        CartDocument cartDocumentFound = cartRepository.findByOrderNumber(orderNumber);
        validateItemNotExists(cartItem.getCode(),cartDocumentFound);
        cartDocumentFound.getProducts().add(cartItem);
        cartRepository.save(cartDocumentFound);

        return CartBuilder.documentToResponse(cartDocumentFound);
    }
    @Override
    public CartResponse updateCartItem(Integer orderNumber, CartItem cartItem) throws ApiRestException {
        validateCartExists(orderNumber);
        validateProductExists(cartItem.getCode());
        var cartDocumentFound =cartRepository.findByOrderNumber(orderNumber);
        validateItemExists(cartItem.getCode(),cartDocumentFound);
        CartItem cartItemFound = getCartItemByCode(cartItem.getCode(), cartDocumentFound.getProducts());
        cartDocumentFound.getProducts().remove(cartItemFound);
        cartDocumentFound.getProducts().add(cartItem);
        cartRepository.save(cartDocumentFound);
        return CartBuilder.documentToResponse(cartDocumentFound);
    }

    @Override
    public void deleteCartItem(Integer orderNumber, String code) throws ApiRestException {
        validateCartExists(orderNumber);
        validateProductExists(code);
        var cartDocumentFound =cartRepository.findByOrderNumber(orderNumber);
        validateItemExists(code,cartDocumentFound);
        var cartItemFound =getCartItemByCode(code,cartDocumentFound.getProducts());
        cartDocumentFound.getProducts().remove(cartItemFound);
        cartRepository.save(cartDocumentFound);

    }

    @Override
    public List<CartItem> getAllItems(Integer orderNumber) throws ApiRestException {

        validateCartExists(orderNumber);
        CartDocument cartDocumentFound = cartRepository.findByOrderNumber(orderNumber);

        return cartDocumentFound.getProducts();
    }

    @Override
    public List<CartItem> generateOrder(Integer orderNumber) throws ApiRestException {

        validateCartExists(orderNumber);
        CartDocument cartDocumentFound = cartRepository.findByOrderNumber(orderNumber);

        return cartDocumentFound.getProducts();
    }


    private void validateCartCreate(CartRequest cartRequest) throws ApiRestException {
        var cart=cartRepository.findByEmail(cartRequest.getEmail());
        if(Objects.nonNull(cart)){
            throw new ApiRestException(cartRequest.getEmail(),"Error, el carrito con ese email ya existe");
        }
        if(Objects.isNull(userRepository.findByEmail(cartRequest.getEmail()))){
            throw new ApiRestException(cartRequest.getEmail(),"Error el usuario con ese email no esta registrado");
        }
    }
    private void validateCartExists(Integer orderNumber) throws ApiRestException {
        var cart = cartRepository.findByOrderNumber(orderNumber);
        if(Objects.isNull(cart)){
            throw new ApiRestException(orderNumber.toString(),"Error, el carrito con ese numero de orden no existe");
        }
    }
    private void validateProductExists(String code) throws ApiRestException {
        var product = productRepository.findByCode(code);
        if(Objects.isNull(product)){
            throw new ApiRestException(code,"Error, el producto con ese code no existe");
        }
    }
    private void validateItemNotExists(String code,CartDocument cartDocument) throws ApiRestException {
        for(CartItem c: cartDocument.getProducts()){
            if(c.getCode().equals(code)){
                throw new ApiRestException(code,"El producto ya esta en el carrito");
            }
        }
    }
    private void validateItemExists(String code,CartDocument cartDocument) throws ApiRestException {
        CartItem itemFound = null;
        for(CartItem c: cartDocument.getProducts()){
            if(c.getCode().equals(code)){
                itemFound =c;
            }
        }
        if ((Objects.isNull(itemFound))){
            throw new ApiRestException(code,"El producto que intenta modificar no esta en el carrito");
        }
    }
    private CartItem getCartItemByCode(String code,List<CartItem> cartItems){
        CartItem itemFound = null;
        for(CartItem cartItem: cartItems){
            if(cartItem.getCode().equals(code)){
                itemFound = cartItem;
            }
        }
        return itemFound;
    }
}
