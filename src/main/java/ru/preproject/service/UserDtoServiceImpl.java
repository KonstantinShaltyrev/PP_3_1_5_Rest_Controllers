package ru.preproject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preproject.dto.UserDto;
import ru.preproject.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final ModelMapper modelMapper;

    @Autowired
    public UserDtoServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User convertUserDto(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public UserDto convertUser(User user) {
        UserDto userDto = new UserDto();
        List<String> rolesList = new ArrayList<>();

        userDto = modelMapper.map(user, UserDto.class);
        user.getRoles().forEach(p -> rolesList.add(p.getRoleName()));
        userDto.setRoles(rolesList);
        return userDto;
    }
}
