package ru.preproject.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.preproject.dto.UserDto;
import ru.preproject.service.UserDtoService;
import ru.preproject.service.UserService;
import ru.preproject.util.UserValidator;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {
    private final UserService userService;
    private final UserDtoService userDtoService;
    private final UserValidator userValidator;

    public AdminRestController(UserService userService, UserDtoService userDtoService, UserValidator userValidator) {
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.userValidator = userValidator;
    }


    @GetMapping(value = "user_list")
    public List<UserDto> getUserList() {
        return userDtoService.convertUser(userService.findAll());
    }

    @DeleteMapping(value = "/delete_user/{id}")
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(userDtoService.convertUser(userService.findAll()));
    }

    @PostMapping(value = "/save_user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto newUserDto, BindingResult bindingResult) {
        userValidator.validate(newUserDto, bindingResult);
        return userService.saveUser(userDtoService.convertUser(newUserDto), newUserDto.getRoles().get(0), bindingResult);
    }

    @PutMapping(value = "/save_user")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto updatedUserDto, BindingResult bindingResult) {
        userValidator.validate(updatedUserDto, bindingResult);
        return userService.saveUser(userDtoService.convertUser(updatedUserDto), updatedUserDto.getRoles().get(0), bindingResult);
    }
}
