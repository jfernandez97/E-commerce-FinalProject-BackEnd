package com.ecommerce.ecommercefinal.model.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDocument {
    @Id
    private String id;
    private String email;
    private String name;
    private String phone;
    private String username;
    private String password;
//    private CartDocument cart;
}
