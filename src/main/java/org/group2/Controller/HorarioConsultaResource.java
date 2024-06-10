package org.group2.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.group2.DTO.HorarioConsultaDTO;
import org.group2.Model.HorarioConsulta;
import org.group2.Service.IHorarioConsultaService;

@Path("/horariosconsulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HorarioConsultaResource {
	
	@Inject
	public IHorarioConsultaService iHorarioConsultaService;

	@GET
	@Path("/{id}")
	//Metodo que devuelve todos los horarios de un solo profesional
	public List<HorarioConsultaDTO> getAll(@PathParam("id") Long id) {
		return iHorarioConsultaService.getHorarios(id);
	}
	
	@POST
	public Response addHorarioConsulta(HorarioConsulta horarioConsulta) {
		try {
			iHorarioConsultaService.addHorarioConsulta(horarioConsulta);
			return Response.ok("Se creo correctamente")
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e)
                    .build();
		}		
	}
}
