package com.ecommerce.ecommercefinal.model.document;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Builder
public class CartItem {
    @Min(1)
    private Integer quantity;
    private String code;
}
