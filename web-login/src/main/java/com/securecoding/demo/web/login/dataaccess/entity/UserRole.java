package com.securecoding.demo.web.login.dataaccess.entity;

import java.math.BigInteger;

public interface UserRole {

    BigInteger getId();

    void setId(BigInteger id);

    void setUserId(BigInteger userId);

    String getRole();

    void setRole(String role);
}
