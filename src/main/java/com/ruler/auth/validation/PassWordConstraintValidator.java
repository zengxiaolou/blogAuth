package com.ruler.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PassWordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length()< 8) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        int count = 0;
        if (hasLetter) count++;
        if (hasNumber) count++;
        if (hasSpecialChar) count++;

        return count >= 2;
    }
}
