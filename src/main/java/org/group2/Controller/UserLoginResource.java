package org.group2.Controller;

import java.util.List;

import org.group2.Model.UserLogin;
import org.group2.Service.IUserLoginService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/userlogin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserLoginResource {
    
    @Inject
    private IUserLoginService iUserLoginService;
    
    //Crear un usuario
    @POST
    @Path("/add")
    @PermitAll
    public void addUser(UserLogin userLogin){
        iUserLoginService.addUser(userLogin);
    }
    
    @PUT
    @Path("/edit/{id}")
    @RolesAllowed("ADMIN")
    public void editUser(@PathParam("id") Long id, UserLogin userLogin ) {
    	iUserLoginService.editUser(id, userLogin.getUsername(), userLogin.getPassword(), userLogin.getRol());
    }
    
    @GET
    @Path("/user/{id}")
    @RolesAllowed("ADMIN")
    public UserLogin findUser(@PathParam("id") Long id) {
    	return iUserLoginService.findUser(id);
    }
    
    @GET
    @RolesAllowed("ADMIN")
    public List<UserLogin> getUsers(){
    	return iUserLoginService.getUsers();
    }
    
    @DELETE
    @Path("/del/{id}")
    @RolesAllowed("ADMIN")
    public void delUser(@PathParam("id") Long id) {
    	iUserLoginService.delUser(id);
    }
}
