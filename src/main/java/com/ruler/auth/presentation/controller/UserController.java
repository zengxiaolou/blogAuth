package com.ruler.auth.presentation.controller;

import com.ruler.auth.application.service.UserService;
import com.ruler.auth.presentation.dto.ApiResponse;
import com.ruler.auth.presentation.dto.EmailCodeDto;
import com.ruler.auth.presentation.dto.UserCreateDto;
import com.ruler.auth.presentation.dto.UserRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
   private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserRespDto>> create(@Validated @RequestBody UserCreateDto userCreateDto) {
        UserRespDto user = userService.create(userCreateDto);
        ApiResponse<UserRespDto> response = ApiResponse.<UserRespDto>builder()
                .result(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register-code")
    public  ResponseEntity<ApiResponse<Boolean>> registerCode(@Validated @RequestBody EmailCodeDto emailCodeDto) {
        Boolean res = userService.getEmailCode(emailCodeDto);
        ApiResponse.Metadata metadata = ApiResponse.Metadata.builder()
                .message("验证码已发送")
                .build();
        ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                .result(res).metadata(metadata)
                .build();
        return ResponseEntity.ok(response);
    }
}
