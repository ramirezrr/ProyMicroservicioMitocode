package com.mitocode.microservices.authentication_server_jwt.expose.web;

import com.mitocode.microservices.authentication_server_jwt.model.dto.UserCredentials;
import com.mitocode.microservices.authentication_server_jwt.model.dto.UserRegister;
import com.mitocode.microservices.authentication_server_jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegister userRegister) {
        return ResponseEntity.ok(authService.register(userRegister));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserCredentials userCredentials) {
        return ResponseEntity.ok(authService.authenticate(userCredentials));
    }


}
