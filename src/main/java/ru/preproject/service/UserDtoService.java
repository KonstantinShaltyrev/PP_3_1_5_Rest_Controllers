package ru.preproject.service;

import ru.preproject.dto.UserDto;
import ru.preproject.model.User;

import java.util.List;

public interface UserDtoService {
    User convertUser(UserDto userDto);
    UserDto convertUser(User user);
    List<UserDto> convertUser(List<User> users);
}
