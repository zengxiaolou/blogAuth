package com.ruler.auth.application.interfaces;

public interface CustomerMailService {
    void sendSimpleEmail(String to, String subject, String content);
}
