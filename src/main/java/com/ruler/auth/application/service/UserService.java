package com.ruler.auth.application.service;

import com.ruler.auth.domain.model.User;
import com.ruler.auth.infrastructure.repository.UserRepository;
import com.ruler.auth.presentation.dto.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User create(UserCreateDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.create(userDto.getUsername(), encodedPassword);
        return userRepository.save(user);
    }
}