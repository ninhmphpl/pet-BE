package com.example.boot.controller;

import com.example.boot.exception.UsernameExist;
import com.example.boot.model.Customer;
import com.example.boot.model.User;
import com.example.boot.security.jwt.JwtResponse;
import com.example.boot.security.jwt.JwtService;
import com.example.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getUsername(), currentUser.getRole()));
    }
    @PostMapping("/signup/{username}/{password}")
    public ResponseEntity<?> signupCustomer (@RequestBody Customer customer, @PathVariable String username, @PathVariable String password) throws UsernameExist {
        password = passwordEncoder.encode(password);
        return new ResponseEntity<>(userService.signUpCustomer(customer,username,password),HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
