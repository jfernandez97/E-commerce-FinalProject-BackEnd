package com.ecommerce.ecommercefinal.model.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductRequest {
    @NotBlank
    @NotNull
    @Length(min = 1,max = 20)
    private String code;
    @NotBlank
    private String category;
    @NotNull
    @Min(0)
    private Double price;
    private String description;

}
