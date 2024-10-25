package com.securecoding.demo.sql.injection.dataacces.repository;

import com.securecoding.demo.sql.injection.dataacces.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository {
}
