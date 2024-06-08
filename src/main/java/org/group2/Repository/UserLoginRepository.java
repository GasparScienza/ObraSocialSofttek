package org.group2.Repository;

import org.group2.Model.UserLogin;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserLoginRepository implements PanacheRepository<UserLogin>{

}
