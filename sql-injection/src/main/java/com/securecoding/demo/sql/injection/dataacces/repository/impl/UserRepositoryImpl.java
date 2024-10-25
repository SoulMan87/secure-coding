package com.securecoding.demo.sql.injection.dataacces.repository.impl;

import com.securecoding.demo.sql.injection.dataacces.entity.UserEntity;
import com.securecoding.demo.sql.injection.dataacces.repository.CustomUserRepository;
import com.securecoding.demo.web.login.dataaccess.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    private static final Logger LOG = LoggerFactory.getLogger (UserRepositoryImpl.class);

    private static final String FIND_USER_BY_USERNAME_AND_PASSWORD =
            "select id, username, password, name, surname " +
            "from users u " +
            "where u.username = '%s' " +
            "and u.password = '%s'";

    private static final String FIND_USER_BY_USERNAME =
            "select id, username, password, name, surname " +
            "from users u " +
            "where u.username = '%s'";


    private static final String FIND_USER_BY_USERID =
            "select id, username, password, name, surname " +
            "from users u " +
            "where u.id = '%s'";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        var query = entityManager.createNativeQuery (FIND_USER_BY_USERNAME_AND_PASSWORD);
        query.setParameter ("username", username);
        query.setParameter ("password", password);
        return getUserFromQuery (query);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        var query = entityManager.createNativeQuery (FIND_USER_BY_USERNAME);
        query.setParameter ("username", username);
        return getUserFromQuery (query);
    }

    @Override
    public Optional<User> findUserByUseId(String userId) {
        var query = entityManager.createNativeQuery (FIND_USER_BY_USERID);
        query.setParameter ("userId", userId);
        return getUserFromQuery (query);
    }

    private Optional<User> getUserFromQuery(Query query) {
        List<Object[]> result = query.getResultList ();
        if (result == null || result.isEmpty ()) {
            return Optional.empty ();
        }
        LOG.debug ("User found with {}", query);
        return Optional.of (mapResultToUser (result.get (0)));
    }

    private User mapResultToUser(Object[] result) {
        UserEntity user = new UserEntity ();
        user.setId (new BigInteger (result[0].toString ()));
        user.setUsername (result[1].toString ());
        user.setPassword (result[2].toString ());
        user.setName (result[3].toString ());
        user.setSurname (result[4].toString ());
        return user;
    }
}
