package com.coremaker.coding.service;

import com.coremaker.coding.dto.AuthenticationResponse;
import com.coremaker.coding.dto.UserData;
import com.coremaker.coding.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserData getUserInfo(String userName) {
        final var user = userRepo.findByEmail(userName);
        return UserData.of(user.getName(), user.getEmail(), user.getPassword());
    }

    public AuthenticationResponse registerUser(UserData userData) {
        final var user = new com.coremaker.coding.model.UserData();
        user.setEmail(userData.getEmail());
        user.setName(userData.getName());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepo.save(user);
        var jwtToken = generateTokenBasedOn(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(UserData userData) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getEmail(), userData.getPassword()));
        var user = userRepo.findByEmail(userData.getEmail());
        var jwtToken = generateTokenBasedOn(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private String generateTokenBasedOn(final com.coremaker.coding.model.UserData userData) {
        final var authorities = List.of(new SimpleGrantedAuthority("USER"));
        return jwtService.generateToken(new User(userData.getEmail(), userData.getPassword(), authorities));
    }
}
