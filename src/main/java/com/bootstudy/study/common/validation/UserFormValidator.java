package com.bootstudy.study.common.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserFormValidator extends AbstractValidator<UserForm> {
    @Override
    protected void doValidate(UserForm form, Errors errors) {
        // 확인용 암호 일치여부 체크
        if(!form.getPassword().equals(form.getPasswordConfirm())) {
            errors.rejectValue("password", "users.unmatchPassword");
            errors.rejectValue("passwordConfirm", "users.unmatchPassword");
        }
    }
}
