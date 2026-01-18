package com.daw.backForms.infrastructure.adapters.outputs.persistence;

import com.daw.backForms.domain.models.User;
import com.daw.backForms.domain.ports.out.UserRepositoryPort;
import com.daw.backForms.infrastructure.adapters.outputs.persistence.entity.UserEntity;
import com.daw.backForms.infrastructure.adapters.outputs.persistence.mapper.UserMapper;
import com.daw.backForms.infrastructure.adapters.outputs.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository springDataUserRepository;
    private final UserMapper userMapper;

    public JpaUserAdapter(SpringDataUserRepository springDataUserRepository, UserMapper userMapper) {
        this.springDataUserRepository = springDataUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = springDataUserRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entity = springDataUserRepository.findByEmail(email);
        return entity.map(userMapper::toDomain);
    }
}