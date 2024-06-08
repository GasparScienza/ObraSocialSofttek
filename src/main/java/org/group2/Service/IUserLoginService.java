package org.group2.Service;

import java.util.List;
import java.util.Set;

import org.group2.Model.UserLogin;


public interface IUserLoginService {
    public void addUser(UserLogin userLogin);
    public UserLogin defaultUser(String email, String contraseña,Set<String> rol);
    public void delUser(Long id);
    public List<UserLogin> getUsers();
    public UserLogin findUser(Long id);
    public String editUser(Long id, UserLogin userLogin);
}
