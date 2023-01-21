package com.coremaker.coding.controller;

import com.coremaker.coding.dto.UserData;
import com.coremaker.coding.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserInformationController {

    private final UsersService usersService;

    @GetMapping("/info")
    public ResponseEntity<UserData> getInfo(@RequestParam String email) {
        return ResponseEntity.ok(usersService.getUserInfo(email));
    }
}
