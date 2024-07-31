package ru.preproject.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.preproject.dto.UserDto;
import ru.preproject.service.UserDtoService;
import ru.preproject.service.UserService;
import ru.preproject.util.UserValidator;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest")
public class CommonRestController {

    private final UserService userService;
    private final UserDtoService userDtoService;
    private final UserValidator userValidator;

    public CommonRestController(UserService userService, UserDtoService userDtoService, UserValidator userValidator) {
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.userValidator = userValidator;
    }


    @GetMapping(value = "/user/current_user")
    public UserDto getCurrentUser(Principal principal) {
        return userDtoService.convertUser(userService.findUser(principal.getName()).get());
    }

    @GetMapping(value = "/admin/user_list")
    public List<UserDto> getUserList() {
        return userService.findAll().stream().map(userDtoService::convertUser).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/admin/delete_user/{id}")
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(userService.findAll().stream().map(userDtoService::convertUser).collect(Collectors.toList()));
    }

    @PostMapping(value = "/admin/save_user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto newUserDto, BindingResult bindingResult) {
        userValidator.validate(newUserDto, bindingResult);
        return userService.saveUser(userDtoService.convertUserDto(newUserDto), newUserDto.getRoles().get(0), bindingResult);
    }

    @PutMapping(value = "/admin/save_user")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto updatedUserDto, BindingResult bindingResult) {
        userValidator.validate(updatedUserDto, bindingResult);
        return userService.saveUser(userDtoService.convertUserDto(updatedUserDto), updatedUserDto.getRoles().get(0), bindingResult);
    }
}


