package com.ecommerce.ecommercefinal.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class MessageErrorHandle {
    @ResponseBody
    @ExceptionHandler(ApiRestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage messageErrorHandle(ApiRestException ex){
        log.error(ex.getMessage());
        return ErrorMessage.of(ex.getCode(),ex.getMessage());
    }
}
