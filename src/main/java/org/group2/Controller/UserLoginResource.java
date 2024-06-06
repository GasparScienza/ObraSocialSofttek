package org.group2.Controller;

import org.group2.Model.UserLogin;
import org.group2.Service.IUserLoginService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/userlogin")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class UserLoginResource {
    
    @Inject
    private IUserLoginService iUserLoginService;

    @POST
    public void addUser(UserLogin userLogin){
        iUserLoginService.addUser(userLogin);
    }
}
