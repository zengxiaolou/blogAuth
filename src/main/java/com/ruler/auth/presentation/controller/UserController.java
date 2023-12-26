package com.ruler.auth.presentation.controller;

import com.ruler.auth.application.service.UserService;
import com.ruler.auth.presentation.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
   private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<TokenResponse>> create(@Validated @RequestBody UserCreateDto userCreateDto) {
        String token = userService.create(userCreateDto);
        TokenResponse tokenObj =TokenResponse.builder().token(token).build();
        return ResponseEntity.ok(ApiResponse.successWithMessage("注册成功", tokenObj));
    }

    @PostMapping("/register/code")
    public  ResponseEntity<ApiResponse<Boolean>> registerCode(@Validated @RequestBody EmailCodeDto emailCodeDto) {
        return ResponseEntity.ok( userService.getEmailCode(emailCodeDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Validated @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto.getUsername(), loginDto.getPassword()));
    }
}
