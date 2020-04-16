package com.bootstudy.study.common.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserForm {
    private static final long serivalVerionUID = -123719238L;

    Long id;

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastname;

    @NotEmpty
    String password;

    @NotEmpty
    String passwordConfirm;

    @NotEmpty
    @Email
    String email;

    @Digits(fraction = 0, integer = 10) // 숫자 어노테이션
    String tel;
}
