package com.daw.backForms.domain.ports.in;

import com.daw.backForms.application.dtos.UserRequest;
import com.daw.backForms.domain.models.User;

public interface RegisterUserUseCase {
    User register(UserRequest request);
}