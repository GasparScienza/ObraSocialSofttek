package org.group2.Controller;

import org.group2.Model.Administrador;
import org.group2.Model.ProfesionalMedico;
import org.group2.ServiceImpl.AdministradorService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministradorResource {
	
	@Inject
	AdministradorService administradorService;
	
	@POST
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico) {
		administradorService.saveProfesionalMedico(profesionalMedico);
	}
	
	@GET
	public void getCartillaMedicos() {
		administradorService.getAllProfesionalMedico();
	}
	
	@GET
	public void getProfesionalMedico(Long id) {
		administradorService.getByIdProfesionalMedico(id);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteProfesionalMedico(Long id) {
		administradorService.deleteByIdProfesionalMedico(id);
	}
	
	@POST
	public void saveAdministrador(Administrador admin) {
		administradorService.saveAdministrador(admin);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteAdministrador(Long id) {
		administradorService.deleteByIdAdministrador(id);
	}
	
	
	/*
	@PUT
	public void modificarMedico(ProfesionalMedico profesionalMedico) {
		profesionalRepository.getEntityManager().merge(profesionalMedico);
	}
	*/
}
