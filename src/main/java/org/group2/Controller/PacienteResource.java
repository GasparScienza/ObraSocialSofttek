package org.group2.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.group2.DTO.PacienteDTO;
import org.group2.Model.Paciente;
import org.group2.Service.IPacienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

	@Inject
	private IPacienteService pacienteService;
	
	@POST
	@Path("/add")
	@RolesAllowed("ADMIN")
	public Response addPaciente(Paciente paciente) {
		try {
			pacienteService.addPaciente(paciente);
			return Response.ok("Paciente creado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el paciente: " + e.getMessage())
                    .build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@RolesAllowed({"ADMIN", "PACIENTE"})
	public Response editPaciente(@PathParam("id") Long id, Paciente paciente) {
		try {
			pacienteService.editPaciente(id, paciente);
			return Response.ok("Turno Medico editado correctamente").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al editar el paciente: " + e.getMessage())
                    .build();
		}
	}
	
	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN", "PROFESIONAL"})
	public Response findPaciente(@PathParam("id") Long id) {
		Optional<Paciente> optionalPaciente = pacienteService.findPaciente(id);
        // Verificar si el Optional contiene un paciente
        if (optionalPaciente.isPresent()) {
            PacienteDTO pacienteDTO = new PacienteDTO(optionalPaciente.get());
            return Response.ok(pacienteDTO).build();
        } else {
            return Response.status(Status.NOT_FOUND)
                    .entity("Paciente no encontrado")
                    .build();
        }
	}
	
	@GET
	//@RolesAllowed({"ADMIN", "PROFESIONAL"})
	@Operation(summary = "Pacientes",description="Lista de Pacientes registrados")
	@APIResponse(responseCode = "404", description = "Not Found")
	public Response getPacientes() {
		List<Paciente> pacientes = pacienteService.getPacientes();
		List<PacienteDTO> pacientesDTO;
		if(pacientes == null || pacientes.isEmpty()) {
			return Response.status(Status.NOT_FOUND)
                    .entity("No existe ningun paciente")
                    .build();
		}else {
			pacientesDTO = pacientes.stream()//convierte la lista en un flujo de elementos 
	                .map(PacienteDTO::new)//aplica una función a cada elemento del flujo, crea un nuevo PacienteDTO para cada paciente
	                .collect(Collectors.toList());//Convierte el flujo de elementos en una lista
	        return Response.ok(pacientesDTO).build();
		}
	}
	
}
