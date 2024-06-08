package org.group2.ServiceImpl;

import java.util.List;
import java.util.Optional;
import org.group2.Model.Receta;
import org.group2.Model.TurnoMedico;
import org.group2.Repository.RecetaRepository;
import org.group2.Service.IRecetaService;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RecetaService implements IRecetaService{

	@Inject
	private RecetaRepository recetaRepository;
	@Inject
	private TurnoMedicoService turnoMedicoService;
	@Inject
    private SecurityIdentity securityIdentity;
	
	
	@Override
	public void addReceta(Receta receta) {
		recetaRepository.persist(receta);
	}

	@Override
	public List<Receta> getRecetas() {
		return recetaRepository.listAll();
	}

	@Override
	public void delReceta(Long id) {
		recetaRepository.deleteById(id);
	}
	
	//Metodo para traer la receta de un paciente,
	//comprobando si la receta le corresponde al paciente que esta tratando de obtener la receta
	@Override
	public Receta findReceta(Long id) {
		TurnoMedico turno = turnoMedicoService.findTurno(id);
		if (turno != null && turno.getPaciente() != null && turno.getPaciente().getUser() != null &&
	            turno.getPaciente().getUser().getUsername().equals(securityIdentity.getPrincipal().getName())) {
	        Optional<Receta> optionalReceta = recetaRepository.findByTurnoMedicoId(id);
	        if (optionalReceta.isPresent()) {
	            return optionalReceta.get();
	        }else {
	            throw new RuntimeException("No se encontró ninguna receta para el turno médico con ID: " + id);
	        }
	    } else {
	        throw new RuntimeException("No está autorizado para acceder a la receta del turno médico con ID: " + id);
	    }
	}
	
	@Override
	public String editReceta(Long id, Receta receta) {
		try {
			if(id != null) {
				Receta r = this.findReceta(id);
				if(r == null) {
					throw new RuntimeException("La receta no existe.");
				}
				
				if(receta.getDiagnostico() != null) {
					r.setDiagnostico(receta.getDiagnostico());
				}
				if(receta.getMedicamento() != null) {
					r.setMedicamento(receta.getMedicamento());
				}
				if(receta.getTurnoMedico() != null) {
					r.setTurnoMedico(receta.getTurnoMedico());
				}
				
				recetaRepository.getEntityManager().merge(r);
				return "Receta editado exitosamente.";
			}else {
				throw new RuntimeException("El ID de la receta no puede ser nulo.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	

}
