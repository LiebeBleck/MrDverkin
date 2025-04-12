package org.example.mrdverkin.controllers.api;

import org.example.mrdverkin.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @PostMapping("/login")
    public ResponseEntity<String> sayHello(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return ResponseEntity.ok("Login:" + username + ", password:" + password);
    }
}

