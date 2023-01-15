package com.coremaker.coding.controller;

import com.coremaker.coding.dto.AuthenticationResponse;
import com.coremaker.coding.dto.UserData;
import com.coremaker.coding.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserAuthenticationController {

    private final UsersService usersService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserData userData) {
        return ResponseEntity.ok(usersService.registerUser(userData));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody UserData userData) {
        return ResponseEntity.ok(usersService.authenticateUser(userData));
    }

}
