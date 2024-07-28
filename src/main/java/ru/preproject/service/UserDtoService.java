package ru.preproject.service;

import ru.preproject.dto.UserDto;
import ru.preproject.model.User;

public interface UserDtoService {
    User convertToUser(UserDto userDto);
    UserDto convertToUserDto(User user);
}
