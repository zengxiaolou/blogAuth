package com.ruler.auth.domain.interfaces;

public interface RedisService {
    void saveCode(String key, String value);
    Long getRemainingExpireTimeInSeconds(String key);
}
