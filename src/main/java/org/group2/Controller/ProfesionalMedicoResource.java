package org.group2.Controller;

import java.util.List;

import org.group2.Model.ProfesionalMedico;
import org.group2.Repository.ProfesionalMedicoRepository;

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

@Path("/medicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesionalMedicoResource {

	@Inject
	ProfesionalMedicoRepository profesionalRepository;
	
	@GET
	public List<ProfesionalMedico> cartillaMedicos(){
		return profesionalRepository.listAll();
	}
	
	
	@POST
	public void agregarMedico(ProfesionalMedico profesionalMedico){
		profesionalRepository.persist(profesionalMedico);
	}
	
	@PUT
	public void modificarMedico(ProfesionalMedico profesionalMedico) {
		profesionalRepository.getEntityManager().merge(profesionalMedico);
	}
	
	@DELETE
	@Path("/{id}")
	public void eliminarMedico(@PathParam("id") Long id) {
		profesionalRepository.deleteById(id);
	}
	
	
	
	
	
	
	
}
