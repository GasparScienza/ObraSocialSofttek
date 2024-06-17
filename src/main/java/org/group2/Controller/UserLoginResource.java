package org.group2.Controller;

import org.group2.DTO.UserLoginDTO;
import org.group2.Model.ProfesionalMedico;
import org.group2.Model.UserLogin;
import org.group2.Service.IUserLoginService;
import io.quarkus.security.identity.SecurityIdentity;
import io.swagger.annotations.ApiOperation;
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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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
    //@ApiOperation(	value = "carga un usuario", notes ="carga un usuario en la base de datos",
	//response = UserLogin.class, tags = "UserLogin")
    //TODO corregir el error de que no me carga un usuario en la swagger ui, y anotar lo corregido
    public Response addUser(UserLogin userLogin){
    	try {
    		iUserLoginService.addUser(userLogin);
    		return Response.ok("Usuario creado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el usuario: " + e.getMessage())
                    .build();
		}
    }
    
    @PUT
    @Path("/edit/{id}")
    @RolesAllowed("ADMIN")
    public Response editUser(@PathParam("id") Long id, UserLogin userLogin ) {
    	try {
    		iUserLoginService.editUser(id, userLogin);
    		return Response.ok("Usuario editado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al editar el usuario: " + e.getMessage())
                    .build();
		}
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response findUser(@PathParam("id") Long id) {
    	UserLogin user = iUserLoginService.findUser(id);
    	UserLoginDTO us;
        if (user == null) {
            return Response.status(Status.NOT_FOUND)
                    .entity("Usuario no encontrado")
                    .build();
        }else {
        	us = new UserLoginDTO(user.getUsername(), user.getRol());
        }
        return Response.ok(us).build();
    }
    
    @DELETE
    @Path("/del/{id}")
    @RolesAllowed("ADMIN")
    public Response delUser(@PathParam("id") Long id) {
    	UserLogin user = iUserLoginService.findUser(id);
        if (user == null) {
            return Response.status(Status.NOT_FOUND)
                    .entity("Usuario no encontrado")
                    .build();
        }else {
        	iUserLoginService.delUser(id);
        	return Response.ok("Usuario Eliminado correctamente").build();
        }
    }
}
