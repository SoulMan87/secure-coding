package com.securecoding.demo.sql.injection.dataacces.repository;

import com.securecoding.demo.sql.injection.dataacces.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<List<UserRoleEntity>> findByUserId(BigInteger userId);
}
