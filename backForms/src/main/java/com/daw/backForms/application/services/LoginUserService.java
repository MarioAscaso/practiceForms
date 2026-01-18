package com.daw.backForms.application.services;

import com.daw.backForms.application.dtos.LoginRequest;
import com.daw.backForms.domain.models.User;
import com.daw.backForms.domain.ports.in.LoginUserUseCase;
import com.daw.backForms.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements LoginUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public LoginUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User login(LoginRequest request) {
        User user = userRepositoryPort.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }
}