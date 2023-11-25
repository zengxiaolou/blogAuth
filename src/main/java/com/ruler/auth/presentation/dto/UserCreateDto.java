package com.ruler.auth.presentation.dto;

import com.ruler.auth.validation.ValidPassword;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserCreateDto {
    @NonNull
    @Size(min = 3, max = 32)
    private String username;

    @ValidPassword
    private String password;

    @NonNull
    @Size(min = 6, max = 6)
    private Integer code;
}
