package com.bootstudy.study.validator;

import com.bootstudy.study.config.AppConfig;
import com.bootstudy.study.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Slf4j
public class ValidatorDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        User user = new User();
        user.setId(null);
        user.setFirstName("SEJIN");
        user.setLastName("OH");

        Validator userValidator = context.getBean("userValidator", Validator.class);
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(user, "1");

        ValidationUtils.invokeValidator(userValidator, user, result);

        List<ObjectError> errors = result.getAllErrors();
        log.info("유효성 검증 에러 갯수 : " + errors.size());
        errors.forEach(e -> log.info(e.getCode()));
    }
}
