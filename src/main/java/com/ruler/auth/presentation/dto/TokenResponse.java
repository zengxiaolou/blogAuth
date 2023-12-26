package com.ruler.auth.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class TokenResponse {
    private String token;
}
