package com.ecommerce.ecommercefinal.iterceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectAfterCart {
    @After("execution(* com.ecommerce.ecommercefinal.service.implementation.CartServiceImpl.*(..))")
    void afterMethods(){
        log.info("Se ejecuto el after advice luego de la ejecucion de un metodo de la capa CartService");
    }
    @AfterThrowing("execution(* com.ecommerce.ecommercefinal.service.implementation.CartServiceImpl.*(..))")
    void afterThrowingMethods(){
        log.info("Se ejecuto el after advice luego de una excepcion de un metodo de la capa CartService");
    }
}
