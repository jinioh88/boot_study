package com.bootstudy.study.web;

import com.bootstudy.study.validator.EqualsPropertyValues;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;

@EqualsPropertyValues(property = "password", comparingProperty = "reEnteredPassword")
public class AccountSearchForm implements Serializable {
    interface FreeAccount extends Default {}
    interface PayAccount extends Default {}

    @NotNull
    private String password;

    private String reEnteredPassword;

    @Size(min = 1, max = 1)
    private String type;

    @Size.List({@Size(max = 0, groups = FreeAccount.class), @Size(min = 14, max = 16, groups = PayAccount.class)})
    private String cardNo;
}
