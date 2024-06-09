package org.group2.ServiceImpl;

import java.util.List;

import org.group2.Model.ProfesionalMedico;

import org.group2.Repository.ProfesionalMedicoRepository;
import org.group2.Service.IProfesionalMedicoService;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ProfesionalMedicoService implements IProfesionalMedicoService{
	
	@Inject
	private ProfesionalMedicoRepository profesionalMedicoRepository;

	@Override
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico) {
		profesionalMedicoRepository.persist(profesionalMedico);
		
	}

	@Override
	public ProfesionalMedico getByIdProfesionalMedico(Long id) {
		return getByIdProfesionalMedico(id);
		
	}

	@Override
	public List<ProfesionalMedico> getAllProfesionalMedico() {
		return profesionalMedicoRepository.listAll();
		
	}

	@Override
	public void deleteByIdProfesionalMedico(Long id) {
		profesionalMedicoRepository.deleteById(id);
		
	}

	@Override
	public String updateByIdProfesionalMedico(Long id, ProfesionalMedico profesionalMedico) {
		try {
			if (id != null) {
				ProfesionalMedico p = this.getByIdProfesionalMedico(id);
				if(p == null) {
					throw new RuntimeException("El profesional medico no existe");
				}
				if(profesionalMedico.getNombreProfesional()!=null) {
					p.setNombreProfesional(profesionalMedico.getNombreProfesional());
				}
				if(profesionalMedico.getEmailProfesional()!= null) {
					p.setEmailProfesional(profesionalMedico.getEmailProfesional());
				}
				if(profesionalMedico.getEspecialidad()!=null) {
					p.setEspecialidad(profesionalMedico.getEspecialidad());
				}
				if(profesionalMedico.getUbicacionConsulta()!=null) {
					p.setUbicacionConsulta(profesionalMedico.getUbicacionConsulta());;
				}
				profesionalMedicoRepository.getEntityManager().merge(p);
				return "Profesional Medico editado exitosamente";
			}else {
				throw new RuntimeException("El ID del profesional medico no existe");
			}
			
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
		
	}
	


}
