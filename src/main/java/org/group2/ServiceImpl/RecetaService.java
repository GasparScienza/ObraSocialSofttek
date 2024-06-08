package org.group2.ServiceImpl;

import java.util.List;

import org.group2.Model.Receta;
import org.group2.Repository.RecetaRepository;
import org.group2.Service.IRecetaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RecetaService implements IRecetaService{

	@Inject
	private RecetaRepository recetaRepository;
	
	
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
