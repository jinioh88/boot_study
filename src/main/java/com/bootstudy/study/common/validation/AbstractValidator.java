package com.bootstudy.study.common.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// 공통용
@Slf4j
public abstract class AbstractValidator<T> implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked") // 경고를 제외시키는 어노테이션
    @Override
    public void validate(final Object target, final Errors errors) {
        try {
            boolean hasErrors = errors.hasErrors();

            if(!hasErrors || passThruBeanValidation(hasErrors)) {
                doValidate((T) target, errors);
            }
        } catch (RuntimeException e) {
            log.error("validate error", e);
            throw e;
        }
    }

    protected abstract void doValidate(T target, Errors errors);

    protected boolean passThruBeanValidation(boolean hasErrors) {
        return false;
    }
}
