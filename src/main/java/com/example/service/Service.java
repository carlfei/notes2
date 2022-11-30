package com.example.service;

import com.example.model.Libros;
import com.example.repository.Biblioteca;
import com.example.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class Service {

    private final Biblioteca biblioteca;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, biblioteca.findByUsername(username).getAppUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    public String signup(Libros libros) {
        if (!biblioteca.existsByUsername(libros.getUsername())) {
            libros.setPassword(passwordEncoder.encode(libros.getPassword()));
            biblioteca.save(libros);
            return jwtTokenProvider.createToken(libros.getUsername(), libros.getAppUserRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }





}
