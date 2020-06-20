package com.bootstudy.study.data;

import com.bootstudy.study.validator.AlphaNumeric;
import com.bootstudy.study.web.AccountSearchForm;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Singer {
    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    private String lastName;

    @NotNull
    private Genre genre;

    private Gender gender;

    @AlphaNumeric
    private String couponCode;

    public boolean isCountrySinger() {
        return genre == Genre.COUNTRY;
    }
}
