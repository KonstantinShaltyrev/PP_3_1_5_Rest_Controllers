package ru.preproject.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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


    @GetMapping(value = "/current_user")
    public UserDto getCurrentUser(Principal principal) {
        return userDtoService.convertToUserDto(userService.findByEmail(principal.getName()).get());
    }

    @GetMapping(value = "/user_list")
    public List<UserDto> getUserList() {
        return userService.findAll().stream().map(userDtoService::convertToUserDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete_user/{id}")
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(userService.findAll().stream().map(userDtoService::convertToUserDto).collect(Collectors.toList()));
    }

    @PostMapping(value = "/create_user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto newUserDto, BindingResult bindingResult) {
        userValidator.validate(newUserDto, bindingResult);
        if (bindingResult.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();

            for (ObjectError error : bindingResult.getAllErrors()) {
                String fieldErrors = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                headers.add(fieldErrors, errorMessage);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        String roleName = newUserDto.getRoles().get(0);
        userService.addUser(userDtoService.convertToUser(newUserDto), roleName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update_user")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto updatedUserDto, BindingResult bindingResult) {
        userValidator.validate(updatedUserDto, bindingResult);
        if (bindingResult.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();

            for (ObjectError error : bindingResult.getAllErrors()) {
                String fieldErrors = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                headers.add(fieldErrors, errorMessage);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        String roleName = updatedUserDto.getRoles().get(0);
        userService.updateUser(userDtoService.convertToUser(updatedUserDto), roleName);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


