package com.securecoding.demo.sql.injection.dataacces.entity;

import com.securecoding.demo.web.login.dataaccess.entity.User;
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
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserEntity implements User {

    @Id
    @NotNull
    private BigInteger id;

    @NotNull
    private String username;

    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer ().getPersistentClass () : o.getClass ();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer ().getPersistentClass () : this.getClass ();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserEntity that = (UserEntity) o;
        return getId () != null && Objects.equals (getId (), that.getId ());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer ().getPersistentClass ().hashCode () : getClass ().hashCode ();
    }
}
