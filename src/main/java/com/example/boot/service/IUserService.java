package com.example.boot.service;

import com.example.boot.exception.UsernameExist;
import com.example.boot.model.Customer;
import com.example.boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends UserDetailsService {
    Optional<User> findByUsername(String username);
    Customer signUpCustomer(Customer customer, String username, String password) throws UsernameExist;
}
