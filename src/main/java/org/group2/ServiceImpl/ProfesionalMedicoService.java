package org.group2.ServiceImpl;

import org.group2.Model.ProfesionalMedico;
import org.group2.Repository.ProfesionalMedicoRepository;
import org.group2.Service.IProfesionalMedicoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProfesionalMedicoService implements IProfesionalMedicoService{
	
	@Inject
	private ProfesionalMedicoRepository profesionalMedicoRepository;
	
	@Override
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico) {
		profesionalMedicoRepository.persist(profesionalMedico);
	}

	@Override
	public void getAll() {
		profesionalMedicoRepository.listAll();
		
	}

	@Override
	public void getById(Long id) {
		profesionalMedicoRepository.findByIdOptional(id);		
	}

	@Override
	public void deleteById(Long id) {
		profesionalMedicoRepository.deleteById(id);		
	}

}
