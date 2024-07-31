package ru.preproject.service;

import ru.preproject.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public void saveRole(Role role);
    public List<Role> findAll();

    public Optional<Role> findRole(String roleName);

    public void deleteRole(Role role);
    public void deleteRole(long id);
    public void checkRoles();
}
