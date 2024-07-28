package ru.preproject.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.preproject.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {

    private long id;

    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min = 3, max = 100, message = "Password should be between 3 and 100 chars")
    private String password;

    @NotEmpty(message = "Firstname shouldn't be empty")
    @Size(min = 2, max = 30, message = "Firstname should be between 2 and 30 chars")
    private String firstName;

    @NotEmpty(message = "Lastname shouldn't be empty")
    @Size(min = 2, max = 30, message = "Lastname should be between 2 and 30 chars")
    private String lastName;

    @PastOrPresent(message = "Birthday should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Name shouldn't be empty")
    private String email;

    @NotEmpty(message = "Phone number shouldn't be empty")
    @Size(min = 2, max = 32, message = "Phone number should be between 5 and 32 chars")
    @Pattern(regexp = "^(\\+)?(\\d{1,4})?[\\s-]?\\(?\\d{1,4}\\)?[\\s.-]?\\d{1,4}[\\s.-]?\\d{1,4}[\\s.-]?\\d{1,4}$",
            message = "Incorrect phone format")
    private String phoneNumber;

    private List<String> roles = new ArrayList<>();



    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
