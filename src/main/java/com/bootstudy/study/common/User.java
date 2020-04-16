package com.bootstudy.study.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    Long id;
    String firstName;
    String lastname;
    String password;
    String passwordConfirm;
    String email;
    String tel;
}
