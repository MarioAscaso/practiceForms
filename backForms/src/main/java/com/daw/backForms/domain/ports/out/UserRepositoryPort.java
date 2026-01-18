package com.daw.backForms.domain.ports.out;

import com.daw.backForms.domain.models.User;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
}