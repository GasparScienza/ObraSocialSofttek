package org.group2.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@UserDefinition 
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserLogin extends PanacheEntity{
    @Username 
    public String username;
    @Password 
    public String password;
    @Roles 
    public String rol;
}
