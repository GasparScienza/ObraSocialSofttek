package org.group2.ServiceImpl;

import java.util.List;
import java.util.Set;

import org.group2.Model.UserLogin;
import org.group2.Repository.UserLoginRepository;
import org.group2.Service.IUserLoginService;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
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
	public String editUser(Long id, String username, String password, Set<String> rol) {
		try {
			if(id != null) {
				UserLogin userLogin = this.findUser(id);
				if(userLogin == null) {
					throw new RuntimeException("El usuario no existe.");
				}
				if(username != null) {
					userLogin.setUsername(username);
				}
				if(password != null) {
					userLogin.setPassword(BcryptUtil.bcryptHash(password));
				}
				if(rol != null) {
					userLogin.setRol(rol);
				}
				userLoginRepository.getEntityManager().merge(userLogin);
				return "Usuario editado exitosamente.";
			}else {
	            throw new RuntimeException("El ID del usuario no puede ser nulo.");
	        }
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

			
	}
    
}
