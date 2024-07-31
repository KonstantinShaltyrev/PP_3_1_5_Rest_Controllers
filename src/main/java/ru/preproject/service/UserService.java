package ru.preproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.preproject.dto.UserDto;
import ru.preproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void saveUser(User user, String roleName);
    public ResponseEntity<String> saveUser(User user, String roleName, BindingResult bindingResult);
    public List<User> findAll();
    public Optional<User> findUser(String username);
    public Optional<User> findUser(long id);
    public void deleteUser(long id);
    public String findRole(Long id);
}