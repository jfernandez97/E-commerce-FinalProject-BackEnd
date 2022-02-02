package com.ecommerce.ecommercefinal.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String code;
    private String category;
    private Double price;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime modificationDate;
    private boolean status;
}
