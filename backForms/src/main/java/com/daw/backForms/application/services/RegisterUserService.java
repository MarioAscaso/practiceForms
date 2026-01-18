package com.daw.backForms.application.services;

import com.daw.backForms.application.dtos.UserRequest;
import com.daw.backForms.domain.models.User;
import com.daw.backForms.domain.ports.in.RegisterUserUseCase;
import com.daw.backForms.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public RegisterUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User register(UserRequest request) {

        if (userRepositoryPort.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email " + request.getEmail() + " ya está registrado.");
        }

        User userToSave = new User(
                null,
                request.getName(),
                request.getSurname(),
                request.getEmail(),
                request.getPassword() // TODO: Más adelante encriptaremos esto aquí
        );

        return userRepositoryPort.save(userToSave);
    }
}