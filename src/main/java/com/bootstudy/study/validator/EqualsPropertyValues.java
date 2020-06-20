package com.bootstudy.study.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EqualsPropertyValuesValidator.class})
@Target({ ANNOTATION_TYPE, TYPE})
@Retention(RUNTIME)
public @interface EqualsPropertyValues {
    String message() default "{com.example.validation.EqualsPropertyValues.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // 유효성 검사기에 필요한 속성
    String property();
    String comparingProperty();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        EqualsPropertyValues[] value();
    }
}
