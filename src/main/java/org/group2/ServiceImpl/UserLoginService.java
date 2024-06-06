package org.group2.ServiceImpl;

import org.group2.Model.UserLogin;
import org.group2.Repository.UserLoginRepository;
import org.group2.Service.IUserLoginService;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserLoginService implements IUserLoginService{

    @Inject
    private UserLoginRepository userLoginRepository;

    @Override
    public void addUser(UserLogin userLogin) {
        userLogin.password = BcryptUtil.bcryptHash(userLogin.password);
        userLoginRepository.persist(userLogin);
    }
    
}
