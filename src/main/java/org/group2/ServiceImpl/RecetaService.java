package org.group2.ServiceImpl;

import java.util.List;

import org.group2.Model.Paciente;
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
	
	
	
	
	
	//Falta implementar validacion de usuario
	@Override
	public Receta findReceta(Long id) {
		return recetaRepository.findByTurnoMedicoId(id).orElse(null);
	}

	
	
	
	
	
	@Override
	public String editReceta(Long id, String medicamentos, String diagnostico, TurnoMedico turnoMedico) {
		try {
			if(id != null) {
				Receta receta = this.findReceta(id);
				if(receta == null) {
					throw new RuntimeException("La receta no existe.");
				}
				
				if(diagnostico != null) {
					receta.setDiagnostico(diagnostico);
				}
				if(medicamentos != null) {
					receta.setMedicamento(medicamentos);
				}
				if(turnoMedico != null) {
					receta.setTurnoMedico(turnoMedico);
				}
				
				recetaRepository.getEntityManager().merge(receta);
				return "Receta editado exitosamente.";
			}else {
				throw new RuntimeException("El ID de la receta no puede ser nulo.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	

}
