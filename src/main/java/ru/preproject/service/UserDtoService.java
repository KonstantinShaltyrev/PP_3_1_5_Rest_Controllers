package ru.preproject.service;

import ru.preproject.dto.UserDto;
import ru.preproject.model.User;

public interface UserDtoService {
    User convertUserDto(UserDto userDto);
    UserDto convertUser(User user);
}
