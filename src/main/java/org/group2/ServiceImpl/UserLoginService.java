package org.group2.ServiceImpl;

import java.util.List;

import org.group2.Model.UserLogin;
import org.group2.Repository.UserLoginRepository;
import org.group2.Service.IUserLoginService;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserLoginService implements IUserLoginService{

    @Inject
    private UserLoginRepository userLoginRepository;

    @Override
    public void addUser(UserLogin userLogin) {
    	//Encriptacion de contraseña
        userLogin.password = BcryptUtil.bcryptHash(userLogin.password);
        userLoginRepository.persist(userLogin);
    }

	@Override
	public void delUser(Long id) {
		userLoginRepository.deleteById(id);
	}

	@Override
	public List<UserLogin> getUsers() {
		return userLoginRepository.listAll();
	}

	@Override
	public UserLogin findUser(Long id) {
		return userLoginRepository.findById(id);
	}

	@Override
	public void editUser(Long id, String username, String password, String rol) {
		UserLogin userLogin = this.findUser(id);
		userLogin.setRol(rol);
		userLogin.setUsername(username);
		userLogin.setPassword(BcryptUtil.bcryptHash(password));
		userLoginRepository.getEntityManager().merge(userLogin);
	}
    
}
