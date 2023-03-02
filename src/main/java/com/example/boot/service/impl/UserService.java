package com.example.boot.service.impl;

import com.example.boot.exception.UsernameExist;
import com.example.boot.model.Customer;
import com.example.boot.model.Roles;
import com.example.boot.model.User;
import com.example.boot.model.UserPrinciple;
import com.example.boot.repository.CustomerRepository;
import com.example.boot.repository.IUserRepository;
import com.example.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = iUserRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(userOptional.get());
    }
    public Customer signUpCustomer(Customer customer, String username, String password) throws UsernameExist {
        if(iUserRepository.existsUserByUsername(username)) throw new UsernameExist();
        User user = new User(null, username, password, Roles.USER);
        user = iUserRepository.save(user);
        customer.setUser(user);
        return customerRepository.save(customer);
    }

}
