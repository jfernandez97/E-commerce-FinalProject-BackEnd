package com.ecommerce.ecommercefinal.model.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRequest {
    @NotBlank
    @NotNull
    @Length(min =4,max = 20)
    private String username;
    @NotBlank
    @NotNull
    @Length(min=4, max = 15)
    private String password;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    private String phone;
}
