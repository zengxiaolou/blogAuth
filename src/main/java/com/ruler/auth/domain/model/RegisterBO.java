package com.ruler.auth.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterBO {
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static RegisterBO create(String password ) {
        var now = LocalDateTime.now();
        return RegisterBO.builder().password(password).createdDate(now).build();
    }

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateTime() {
        var now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

}