package com.bootstudy.study.validator;

import com.bootstudy.study.anootation.CheckCountrySinger;
import com.bootstudy.study.data.Singer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {
   public void initialize(CheckCountrySinger constraint) {
   }

   public boolean isValid(Singer singer, ConstraintValidatorContext context) {
      boolean result = true;
      if(singer.getGenre() != null && (singer.isCountrySinger()
              && (singer.getLastName() == null || singer.getGender() == null))) {
         result = false;
      }

      return result;
   }
}
