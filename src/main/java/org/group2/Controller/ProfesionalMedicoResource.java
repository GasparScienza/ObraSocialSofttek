package org.group2.Controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.group2.DTO.ProfesionalMedicoDTO;
import org.group2.Model.ProfesionalMedico;
import org.group2.Service.IProfesionalMedicoService;

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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/especialistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesionalMedicoResource {

	@Inject
	private IProfesionalMedicoService iProfesionalMedicoService;
	
	//Punto 2 cartilla de medicos
	
	@GET
	@RolesAllowed({"ADMIN", "PACIENTE"})
	@Operation(	summary="Cartilla de especialistas", 
				description = "Devuelve la cartilla de especialistas")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Succesful response"),
			@APIResponse(responseCode = "404", description = "Not Found")
			})
	public List<ProfesionalMedicoDTO> getCartilla(){
		return iProfesionalMedicoService.cartillaMedico();
	}
	
	@POST
	@Path("/add")
	@RolesAllowed("ADMIN")
	@ApiOperation(	value = "carga un medico", notes ="carga un medico en la base de datos",
					response = ProfesionalMedico.class, tags = "ProfesionalMedico")
	public Response saveProfesionalMedico(ProfesionalMedico profesionalMedico) {
		try {
			iProfesionalMedicoService.saveProfesionalMedico(profesionalMedico);
			return Response.ok("ProfesionalMedico creado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el profesionalMedico: " + e.getMessage())
                    .build();
		}
	}
	
	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN"})
	@ApiOperation(value = "buscar medico por id", notes ="busca medico en la base de datos por id",
				response = ProfesionalMedico.class, tags = "ProfesionalMedico")
	public Response getByIdProfesionalMedico(
			@ApiParam(value="Datos del medico", name = "id", example= "1",required=true)
			@PathParam("id") Long id
			) {
		ProfesionalMedico profesionalMedico = iProfesionalMedicoService.getByIdProfesionalMedico(id);
		ProfesionalMedicoDTO profesionalMedicoDTO;
		if(profesionalMedico == null) {
			return Response.status(Status.NOT_FOUND)
                    .entity("Profesional medico no encontrado")
                    .build();
		}else {
			profesionalMedicoDTO = new ProfesionalMedicoDTO();
			
		}
		return Response.ok(profesionalMedicoDTO).build();
	}

	@DELETE
	@Path("/del/{id}")
	@RolesAllowed({"ADMIN"})
	public Response delProfesionalMedico(@PathParam("id") Long id) {
		ProfesionalMedico profesionalMedico= iProfesionalMedicoService.getByIdProfesionalMedico(id);
		if(profesionalMedico == null) {
			return Response.status(Status.NOT_FOUND)
                    .entity("Profesional Medico no encontrada")
                    .build();
		}else {
			iProfesionalMedicoService.deleteByIdProfesionalMedico(id);
			return Response.ok("Profesional Medico eliminado correctamente").build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@RolesAllowed({"ADMIN"})
	public Response updateByIdProfesionalMedico(@PathParam("id") Long id, ProfesionalMedico profesionalMedico) {
		try {			
			iProfesionalMedicoService.updateByIdProfesionalMedico(id, profesionalMedico);
			return Response.ok("Profesional Medico editado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al editar el profesional medico: " + e.getMessage())
                    .build();
		}
	}
	
}
