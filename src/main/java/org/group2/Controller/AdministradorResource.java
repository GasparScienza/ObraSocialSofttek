package org.group2.Controller;

import java.util.List;
import org.group2.Model.Administrador;
import org.group2.Service.IAdministrador;
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

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministradorResource {
	
	@Inject
	IAdministrador iAdministradorService;
	
	@POST
	@RolesAllowed({"ADMIN"})
	public void saveAdministrador(Administrador administrador) {
		iAdministradorService.saveAdministrador(administrador);
	}
	
	@GET
	@RolesAllowed({"ADMIN"})
	@Path("/{id}")
	public void getAdministradorById(@PathParam("id")Long id) {
		iAdministradorService.findByIdAdministrador(id);
	}
	
	@GET
	public List<Administrador> getAllAdmins(){
		return iAdministradorService.getAllAdmins();
	}
	
	@DELETE
	@Path("{id}")
	@RolesAllowed({"ADMIN"})
	public void deleteAdministrador(Long id) {
		iAdministradorService.deleteByIdAdministrador(id);
	}
	
	@PUT
	@Path("/edit/{id}")
	@RolesAllowed({"ADMIN"})
	public void modificarAdministrador(Long id,Administrador administrador) {
		iAdministradorService.updateAdministrador(id,administrador);
	}
}
