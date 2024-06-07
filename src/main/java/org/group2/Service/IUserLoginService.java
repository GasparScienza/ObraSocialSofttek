package org.group2.Service;

import java.util.List;
import java.util.Set;

import org.group2.Model.UserLogin;

import jakarta.ws.rs.core.Response;

public interface IUserLoginService {
    public void addUser(UserLogin userLogin);
    public void delUser(Long id);
    public List<UserLogin> getUsers();
    public UserLogin findUser(Long id);
    public String editUser(Long id, String username, String password, Set<String> rol);
}
