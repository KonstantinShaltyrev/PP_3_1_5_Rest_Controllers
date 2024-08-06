package ru.preproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.preproject.dto.UserDto;
import ru.preproject.service.UserDtoService;
import ru.preproject.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping
    public String printUser() {
        return "user/user_panel";
    }
}
