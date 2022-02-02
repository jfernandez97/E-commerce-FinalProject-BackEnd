package com.ecommerce.ecommercefinal.model.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Builder
@Document("product")
public class ProductDocument {

    @Id
    private String id;
    private String code;
    private Double price;
    private String category;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private boolean status;
}
