package com.ruler.auth.application.interfaces;

public interface ReCaptchaService {
    boolean verify(String token);
}
