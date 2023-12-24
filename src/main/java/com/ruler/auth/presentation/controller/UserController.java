package com.ruler.auth.presentation.controller;

import com.ruler.auth.application.service.UserService;
import com.ruler.auth.presentation.dto.ApiResponse;
import com.ruler.auth.presentation.dto.EmailCodeDto;
import com.ruler.auth.presentation.dto.UserCreateDto;
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
    public ResponseEntity<ApiResponse<String>> create(@Validated @RequestBody UserCreateDto userCreateDto) {
        String token = userService.create(userCreateDto);
        return ResponseEntity.ok(ApiResponse.successWithMessage("注册成功", token));
    }

    @PostMapping("/register-code")
    public  ResponseEntity<ApiResponse<Boolean>> registerCode(@Validated @RequestBody EmailCodeDto emailCodeDto) {
        return ResponseEntity.ok( userService.getEmailCode(emailCodeDto));
    }
}
