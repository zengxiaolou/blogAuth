package com.ruler.auth.application.service;

import com.ruler.auth.application.interfaces.CustomerMailService;
import com.ruler.auth.domain.model.User;
import com.ruler.auth.domain.service.UserDomainService;
import com.ruler.auth.infrastructure.repository.UserRepository;
import com.ruler.auth.presentation.dto.EmailCodeDto;
import com.ruler.auth.presentation.dto.UserCreateDto;
import com.ruler.auth.presentation.dto.UserRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerMailService customerMailService;
    private final UserDomainService userDomainService;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, CustomerMailService customerMailService,
                       UserDomainService userDomainService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customerMailService = customerMailService;
        this.userDomainService = userDomainService;
    }

    public UserRespDto create(UserCreateDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.create(userDto.getUsername(), encodedPassword);
        User saveUser =  userRepository.save(user);
        return new UserRespDto(saveUser.getUsername());
    }

    public Boolean getEmailCode(EmailCodeDto emailCodeDto) {
        String code = userDomainService.getRegisterCode(emailCodeDto.getEmail());
        customerMailService.sendSimpleEmail(emailCodeDto.getEmail(), "小楼的破栈注册邮件", String.format("您的验证吗是： %s", code));
        return true;
    }
}