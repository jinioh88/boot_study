package com.bootstudy.study.common.validation.controller;

import com.bootstudy.study.common.validation.UserForm;
import com.bootstudy.study.common.validation.UserFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserFormValidator userFormValidator;

    @InitBinder("userForm")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    @PostMapping("/new")
    public String newUser(@Validated @ModelAttribute("userForm")UserForm form, BindingResult br,
                          RedirectAttributes attributes) {
        if(br.hasErrors()) {
            // 오류 흐름 코드
            return "redirect:/users/new";
        }
        return "register";
    }
}
