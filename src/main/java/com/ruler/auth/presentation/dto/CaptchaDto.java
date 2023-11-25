package com.ruler.auth.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public class CaptchaDto {
    @NotNull
    private String token;
}
