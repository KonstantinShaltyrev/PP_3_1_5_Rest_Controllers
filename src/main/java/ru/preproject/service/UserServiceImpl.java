package ru.preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.preproject.model.Role;
import ru.preproject.model.User;
import ru.preproject.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void saveUser(User user, String roleName) {
        Set<Role> rolesSet = new HashSet<>();

        roleService.checkRoles();
        if (roleName.equals("ROLE_ADMIN")) {
            rolesSet.add(roleService.findRole("ROLE_ADMIN").get());
        }
        rolesSet.add(roleService.findRole("ROLE_USER").get());
        user.setRoles(rolesSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public ResponseEntity<String> saveUser(User user, String roleName, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();

            for (ObjectError error : bindingResult.getAllErrors()) {
                String fieldErrors = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                headers.add(fieldErrors, errorMessage);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        saveUser(user, roleName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String findRole(Long id) {
        User userById = findUser(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<String> currentRoles = new ArrayList<>();

        userById.getRoles().forEach(p -> currentRoles.add(p.getRoleName()));
        return currentRoles.contains("ROLE_ADMIN") ? "ADMIN" : "USER";
    }
}