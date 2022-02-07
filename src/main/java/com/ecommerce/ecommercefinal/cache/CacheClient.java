package com.ecommerce.ecommercefinal.cache;

public interface CacheClient<T> {
    T save(String key,T data);
    T recover(String key,Class<T> classValue);
}
