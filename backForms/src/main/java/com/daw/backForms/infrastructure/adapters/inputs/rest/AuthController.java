package com.daw.backForms.infrastructure.adapters.inputs.rest;

import com.daw.backForms.application.dtos.UserRequest;
import com.daw.backForms.domain.models.User;
import com.daw.backForms.domain.ports.in.RegisterUserUseCase;
import com.daw.backForms.application.dtos.LoginRequest;
import com.daw.backForms.domain.ports.in.LoginUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;


    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequest request) {
        User createdUser = registerUserUseCase.register(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginRequest request) {
        User loggedUser = loginUserUseCase.login(request);
        return ResponseEntity.ok(loggedUser);
    }
}