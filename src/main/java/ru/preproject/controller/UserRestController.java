package ru.preproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preproject.dto.UserDto;
import ru.preproject.service.UserDtoService;
import ru.preproject.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {
    private final UserService userService;
    private final UserDtoService userDtoService;

    public UserRestController(UserService userService, UserDtoService userDtoService) {
        this.userService = userService;
        this.userDtoService = userDtoService;
    }


    @GetMapping(value = "/user_info")
    public UserDto getCurrentUser(Principal principal) {
        return userDtoService.convertUser(userService.findUser(principal.getName()).get());
    }
}