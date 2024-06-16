package org.group2.Controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.group2.Model.Administrador;
import org.group2.Service.IAdministrador;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	@ApiOperation(value="carga un administrador", notes ="carga un administrador en base de datos",
					response = Administrador.class)
					
	public void saveAdministrador(Administrador administrador) {
		iAdministradorService.saveAdministrador(administrador);
	}
	
	@GET
	@RolesAllowed({"ADMIN"})
	@Path("/{id}")
	@ApiOperation(value="buscar admin por id", notes ="buscar admin en base de datos por id",
					response= Administrador.class)
	@APIResponse(responseCode = "404", description = "Not Found")
	public void getAdministradorById(
			@ApiParam(value="Datos del admin", name = "id", example = "1", required= true)
			@PathParam("id")Long id) {
		iAdministradorService.findByIdAdministrador(id);
	}
	
	@GET
	@Operation(summary="Administradores",
				description="Lista de Administradores")
	@APIResponses(value=
			{
					@APIResponse(responseCode = "200", description ="Succeful respones"),
					@APIResponse(responseCode = "404", description ="Not Found")
			}
	)
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
