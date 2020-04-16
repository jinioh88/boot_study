package com.bootstudy.study.common.validation.controller;

import com.bootstudy.study.common.User;
import com.bootstudy.study.common.file.PdfView;
import com.bootstudy.study.common.mapping.UserService;
import com.bootstudy.study.common.validation.UserForm;
import com.bootstudy.study.common.validation.UserFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserFormValidator userFormValidator;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @InitBinder("userForm")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    @PostMapping("/new")
    public String newUser(@Validated @ModelAttribute("userForm")UserForm form, BindingResult br,
                          RedirectAttributes attributes) {
        var inputUser = modelMapper.map(form, User.class);
        val password = form.getPassword();

        inputUser.setPassword(passwordEncoder.encode(password));

        var createduser = userService.create(inputUser);

        return "redirect:/users/users/show/" + createduser.getId();
    }

    @GetMapping(path = "/download/{filename:.+\\.pdf}")
    public ModelAndView downloadPdf(@PathVariable String filename) {
        val users = userService.findAll();
        val view = new PdfView("reports/users.jrxml", users, filename);

        return new ModelAndView(view);
    }
}
