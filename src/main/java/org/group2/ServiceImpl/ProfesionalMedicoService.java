package org.group2.ServiceImpl;

import org.group2.Model.ProfesionalMedico;
import org.group2.Model.Receta;
import org.group2.Repository.ProfesionalMedicoRepository;
import org.group2.Service.IProfesionalMedicoService;
import org.group2.Model.TurnoMedico;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProfesionalMedicoService implements IProfesionalMedicoService{
	
	@Inject
	private ProfesionalMedicoRepository profesionalMedicoRepository;
	

	@Override
	public void cancelarTurno(Long IdTurnoMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generarReceta(Receta receta) {
		// TODO Auto-generated method stub
		
	}

}
