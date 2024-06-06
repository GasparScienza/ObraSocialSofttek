package org.group2.Service;

import java.util.List;

import org.group2.Model.UserLogin;

public interface IUserLoginService {
    public void addUser(UserLogin userLogin);
    public void delUser(Long id);
    public List<UserLogin> getUsers();
    public UserLogin findUser(Long id);
    public void editUser(Long id, String username, String password, String rol);
}
