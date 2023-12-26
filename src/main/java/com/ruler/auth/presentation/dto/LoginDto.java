package com.ruler.auth.presentation.dto;

import com.ruler.auth.validation.ValidPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    @NotNull
    @Size(min = 3, max = 32)
    private String username;

    @ValidPassword
    private String password;
}
