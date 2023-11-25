package com.ruler.auth.presentation.controller;

import com.ruler.auth.application.service.UserService;
import com.ruler.auth.domain.model.User;
import com.ruler.auth.presentation.dto.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
   private final UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserCreateDto userCreateDto) {
        User user = userService.create(userCreateDto);
        return ResponseEntity.ok(user);
    }
}
