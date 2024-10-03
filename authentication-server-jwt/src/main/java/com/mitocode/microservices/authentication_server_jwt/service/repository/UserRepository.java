package com.mitocode.microservices.authentication_server_jwt.service.repository;

import com.mitocode.microservices.authentication_server_jwt.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
