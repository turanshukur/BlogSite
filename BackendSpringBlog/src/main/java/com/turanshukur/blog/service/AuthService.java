package com.turanshukur.blog.service;

import com.turanshukur.blog.dto.LoginReguest;
import com.turanshukur.blog.dto.RegisterRequest;
import com.turanshukur.blog.model.User;
import com.turanshukur.blog.repository.UserRepository;
import com.turanshukur.blog.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public void signUp(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public String login(LoginReguest loginReguest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReguest.getUsername(),
                loginReguest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateTooken(authenticate);
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
