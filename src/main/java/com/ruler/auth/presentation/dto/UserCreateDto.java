package com.ruler.auth.presentation.dto;

import com.ruler.auth.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDto {
    @NotNull
    @Size(min = 3, max = 32)
    private String username;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 6, max = 6)
    private String code;

    @NotNull
    @Email
    private String email;
}
