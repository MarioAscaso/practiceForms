package com.daw.backForms.domain.ports.in;

import com.daw.backForms.application.dtos.LoginRequest;
import com.daw.backForms.domain.models.User;

public interface LoginUserUseCase {
    User login(LoginRequest request);
}