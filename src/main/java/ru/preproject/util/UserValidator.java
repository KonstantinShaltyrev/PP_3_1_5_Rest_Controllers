package ru.preproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.preproject.dto.UserDto;
import ru.preproject.model.User;
import ru.preproject.service.UserService;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (userService.findUser(user.getEmail()).isPresent() && userService.findUser(user.getEmail()).get().getId() != user.getId()) {
            errors.rejectValue("email", "", "Account with this Email already exists");
        }
    }
}
