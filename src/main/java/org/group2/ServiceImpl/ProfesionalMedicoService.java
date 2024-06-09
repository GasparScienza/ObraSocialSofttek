package org.group2.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.group2.DTO.ProfesionalMedicoDTO;
import org.group2.Model.ProfesionalMedico;

import org.group2.Repository.ProfesionalMedicoRepository;
import org.group2.Service.IProfesionalMedicoService;
import org.group2.Service.IUserLoginService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ProfesionalMedicoService implements IProfesionalMedicoService{
	
	@Inject
	private ProfesionalMedicoRepository profesionalMedicoRepository;
	@Inject
	private HorarioConsultaService horarioConsultaService;
	@Inject
	private IUserLoginService iUserLoginService;

	@Override
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico) {
		Set<String> rol = Collections.singleton("PROFESIONAL");
		profesionalMedico.setUserLogin(iUserLoginService.defaultUser(
				profesionalMedico.getEmailProfesional(),
				profesionalMedico.getNombreProfesional(),
				rol));
		profesionalMedicoRepository.persist(profesionalMedico);
		
	}

	@Override
	public ProfesionalMedico getByIdProfesionalMedico(Long id) {
		return profesionalMedicoRepository.findById(id);
		
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

	@Override
	public List<ProfesionalMedicoDTO> cartillaMedico() {
		List<ProfesionalMedico> profesionalMedicos = profesionalMedicoRepository.findProfesionalesConHorariosDisponibles();
		List<ProfesionalMedicoDTO> profesionalMedicoDTOs = new ArrayList<>();
		for (ProfesionalMedico profesionalMedico : profesionalMedicos) {
			ProfesionalMedicoDTO profesionalMedicoDTO = new ProfesionalMedicoDTO();
			profesionalMedicoDTO.setEspecialidad(profesionalMedico.getEspecialidad());
			profesionalMedicoDTO.setNombreProfesional(profesionalMedico.getNombreProfesional());
			profesionalMedicoDTO.setUbicacionConsulta(profesionalMedico.getUbicacionConsulta());
			profesionalMedicoDTO.setHorarioConsulta(horarioConsultaService.getHorariosDisponibles(profesionalMedico.getId()));
			profesionalMedicoDTOs.add(profesionalMedicoDTO);
		}
		return profesionalMedicoDTOs;
	}
	


}
