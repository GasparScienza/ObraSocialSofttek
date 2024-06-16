package org.group2.Controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.group2.Model.Receta;
import org.group2.Service.IRecetaService;
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

@Path("/recetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecetaResource {
	@Inject
	private IRecetaService iRecetaService;
	
	@GET
	@RolesAllowed({"ADMIN", "PROFESIONAL"})
	@Operation(summary = "Recetas", description = "Lista de recetas")
	@APIResponse(responseCode = "404", description = "Not Found")
	public List<Receta> getRecetas(){
		return iRecetaService.getRecetas();
	}
	
	@POST
	@Path("/add")
	@RolesAllowed("PROFESIONAL")
	@Operation(summary = "Crear Receta", description = "Solo el usuario Profesional"
			+ "puede crear una receta para un usuario Paciente")
	public Response addReceta(Receta receta) {
		try {
			iRecetaService.addReceta(receta);
    		return Response.ok("Receta creada correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear la receta: " + e.getMessage())
                    .build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@RolesAllowed("PROFESIONAL")
	public Response editReceta(@PathParam("id") Long id, Receta receta) {
		try {
			iRecetaService.editReceta(id, receta);
			return Response.ok("Receta editada correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al editar la receta: " + e.getMessage())
                    .build();
		}
	}
	
	//Punto 5 Descargar receta medica
	@GET
	@Path("/{id}")
	@RolesAllowed("PACIENTE")
	@Operation(summary = "Descarga una receta", description = "Solo un usuario Paciente puede"
			+ "descargar una receta")
	@APIResponse(responseCode = "404",description = "Not Found")
	public Response getReceta(@PathParam("id") Long id) {
		try {
			Receta receta = iRecetaService.findReceta(id);
			return Response.ok(receta).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al buscar la receta: " + e.getMessage())
                    .build();
		}
	}
	
	@DELETE
	@Path("/del/{id}")
	@RolesAllowed({"ADMIN", "PROFESIONAL"})
	public Response delReceta(@PathParam("id") Long id) {
		Receta receta = iRecetaService.findReceta(id);
		if(receta == null) {
			return Response.status(Status.NOT_FOUND)
                    .entity("Receta no encontrada")
                    .build();
		}else {
			iRecetaService.delReceta(id);
			return Response.ok("Usuario Eliminado correctamente").build();
		}
	}
	
}
