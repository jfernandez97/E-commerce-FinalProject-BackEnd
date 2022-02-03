package com.ecommerce.ecommercefinal.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiRestException extends Exception{
    private String code;
    public ApiRestException(String code,String message){
        super(message);
        this.code =code;
    }
}
