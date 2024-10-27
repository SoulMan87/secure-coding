package com.securecoding.demo.sql.injection.dataaccess.entity;

import com.securecoding.demo.web.login.dataaccess.entity.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserRoleEntity implements UserRole {

    @Id
    @NotNull
    private BigInteger id;

    @NotNull
    private BigInteger userId;

    @NotNull
    private String role;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer ().getPersistentClass () : o.getClass ();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer ().getPersistentClass () : this.getClass ();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return getId () != null && Objects.equals (getId (), that.getId ());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer ().getPersistentClass ().hashCode () : getClass ().hashCode ();
    }
}
