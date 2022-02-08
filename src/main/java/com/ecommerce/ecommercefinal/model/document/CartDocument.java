package com.ecommerce.ecommercefinal.model.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document("cart")
public class CartDocument {
    @Id
    private String id;
    //Este atributo es autoincremental
    private Integer orderNumber;
    private String email;
    private LocalDateTime createDate;
    private List<CartItem> products;
    private String status;
    private String deliverAddress;

}
