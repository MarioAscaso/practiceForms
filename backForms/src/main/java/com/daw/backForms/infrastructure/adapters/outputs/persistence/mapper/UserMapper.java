package com.daw.backForms.infrastructure.adapters.outputs.persistence.mapper;

import com.daw.backForms.domain.models.User;
import com.daw.backForms.infrastructure.adapters.outputs.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}