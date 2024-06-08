package org.group2.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.group2.Model.HorarioConsulta;
import org.group2.Service.IHorarioConsultaService;

@Path("/horarios-consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HorarioConsultaResource {
	
	@Inject
	public IHorarioConsultaService iHorarioConsultaService;

	@GET
	public List<HorarioConsulta> getAll() {
		return iHorarioConsultaService.getHorarios();
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
