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
        return ResponseEntity.ok(ApiResponse.successWithMessage("注册成功", user));
    }

    @PostMapping("/register-code")
    public  ResponseEntity<ApiResponse<Boolean>> registerCode(@Validated @RequestBody EmailCodeDto emailCodeDto) {
        return ResponseEntity.ok( userService.getEmailCode(emailCodeDto));
    }
}
