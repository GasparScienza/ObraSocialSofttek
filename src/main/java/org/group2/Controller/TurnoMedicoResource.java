package org.group2.Controller;

import java.util.List;
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
	public List<TurnoMedico> getTurnos(){
		return iTurnoMedicoService.getTurnos();
	}

	//Punto 1 Crear turno medico
	@POST
	@RolesAllowed({"PROFESIONAL", "ADMIN", "PACIENTE"})
	public Response addTurno(TurnoMedico turno) {
		try {
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
