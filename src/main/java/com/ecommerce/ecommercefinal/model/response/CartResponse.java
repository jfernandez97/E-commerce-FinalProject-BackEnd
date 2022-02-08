package com.ecommerce.ecommercefinal.model.response;

import com.ecommerce.ecommercefinal.model.document.CartItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {
    private Integer orderNumber;
    private List<CartItem> products;
    private String email;
    private String status;
    private LocalDateTime createDate;

}
