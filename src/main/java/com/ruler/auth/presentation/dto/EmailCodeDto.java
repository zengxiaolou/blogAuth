package com.ruler.auth.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
public class EmailCodeDto {
    @NotNull(message = "邮箱不能为空")
    @NotEmpty(message= "邮箱不能为空")
    @Email(message = "无效的邮箱")
    private String email;
}
