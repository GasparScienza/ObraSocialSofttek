package org.group2.Controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.group2.Model.TurnoMedico;
import org.group2.Service.ITurnoMedicoService;
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

@Path("/turnos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TurnoMedicoResource {
	
	@Inject
	ITurnoMedicoService iTurnoMedicoService;
	
	@GET
	@Operation(summary = "Turnos", description = "Muestra los turnos disponibles")
	@APIResponse(responseCode = "404", description = "Not Found")
	public List<TurnoMedico> getTurnos(){
		return iTurnoMedicoService.getTurnos();
	}

	//Punto 1 Crear turno medico
	@POST
	//@RolesAllowed({"PROFESIONAL", "ADMIN", "PACIENTE"})
	@Operation(summary = "Crea un turno medico",description = "Crea un turno ya sea con un usuario "
			+ "Paciente, Profesional o Administrador")
	public Response addTurno(TurnoMedico turno) {
		try {
			System.out.println(turno+"turnoController");
			iTurnoMedicoService.addTurno(turno);
			return Response.ok("Turno medico creado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el turno medico: " + e.getMessage())
                    .build();
		}
	}
	
	//Punto 3 Actualizar turno medico
	@PUT
	@Path("/{id}")
	@RolesAllowed("ADMIN")
	public Response editTurno(@PathParam("id") Long id, TurnoMedico turno) {
		try {
			iTurnoMedicoService.editTurno(id, turno);
			return Response.ok("Turno Medico editado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al editar el turno medico: " + e.getMessage())
                    .build();
		}
	}

	@DELETE
	@Path("/{id}")
	@RolesAllowed({"ADMIN", "PACIENTE"})
	@Operation(summary = "Dar de baja un turno medico", description = "Da de baja un turno, esto lo pueden "
			+ "realizar un usuario Paciente o Administrador")
	@APIResponse(responseCode = "404", description = "Not Found")
	public Response elimTurno(@PathParam("id") Long id){
		try {
            String resultado = iTurnoMedicoService.delTurno(id);
            if (resultado.equals("Turno eliminado correctamente")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
            }
        } catch (RuntimeException e) {
            return Response.status(Response.Status.FORBIDDEN)
			.entity("Error al editar el turno medico: " + e.getMessage())
			.build();
        }
	}
}
