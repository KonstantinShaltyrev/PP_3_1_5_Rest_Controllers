package ru.preproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.preproject.dto.UserDto;
import ru.preproject.service.UserDtoService;
import ru.preproject.service.UserService;
import ru.preproject.util.UserValidator;

@Controller
public class AuthController {

    private final UserValidator userValidator;
    private final UserService userService;
    private final UserDtoService userDtoService;

    @Autowired
    public AuthController(UserValidator userValidator, UserService userService, UserDtoService userDtoService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.userDtoService = userDtoService;
    }


    @GetMapping("/")
    public String redirectToLogInPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String openLogInPage() {
        return "auth/signin";
    }



    @GetMapping("/signup")
    public String openRegistration(ModelMap model) {
        model.addAttribute("newUser", new UserDto());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String performRegistration(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        userService.saveUser(userDtoService.convertUserDto(userDto), "ROLE_USER");
        return "redirect:/login";
    }
}
