package com.ruler.auth.domain.service;

import com.ruler.auth.constant.Prefix;
import com.ruler.auth.domain.interfaces.RedisService;
import com.ruler.auth.exception.CodeException;
import com.ruler.auth.exception.ShortIntervalException;
import com.ruler.auth.utils.RandomCodeGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {
    private final RedisService redisService;

    public UserDomainService(RedisService redisService) {
        this.redisService = redisService;
    }

    public String getRegisterCode(String email) {
        Long remainingTime= redisService.getRemainingExpireTimeInSeconds(email+"-register");
        if (remainingTime > 240) {
            Long afterTime = remainingTime - 240;
            throw new ShortIntervalException(String.format("获取验证码频率过快, 请%d秒后重试", afterTime));
        }
        String code = RandomCodeGenerator.generateSixDigitCode();
        redisService.saveCode(email+"-register", code);
        return  code;
    }

    public void VerifyCode(String email, String code) {
        String prefix = Prefix.REGISTER;
        Long remainingTime= redisService.getRemainingExpireTimeInSeconds(email+"-" + prefix);
        if (remainingTime < 0) {
            throw new CodeException("验证码已过期");
        }
        if (!code.equals(redisService.get(email+"-" +prefix))) {
            throw new CodeException("验证码错误");
        }
        redisService.delete(email+prefix);
    }
}