package com.bootstudy.study.validator;

import com.bootstudy.study.data.Singer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SingerValidationService {
    private final Validator validator;

    public Set<ConstraintViolation<Singer>> validationSinger(Singer singer) {
        return validator.validate(singer);
    }
}
