package com.ruler.auth.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserCreateDto {
    private String username;
    private String password;
    private Integer code;
}
