package org.group2.ServiceImpl;

import org.group2.Model.Administrador;
import org.group2.Model.Paciente;
import org.group2.Model.ProfesionalMedico;
import org.group2.Model.TurnoMedico;
import org.group2.Repository.AdministradorRepository;
import org.group2.Repository.ProfesionalMedicoRepository;
import org.group2.Service.IAdministrador;

import jakarta.inject.Inject;

public class AdministradorService implements IAdministrador{
	
	@Inject
	ProfesionalMedicoRepository profesionalMedicoRepository;
	@Inject
	AdministradorRepository administradorRepository;

	@Override
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico) {
		profesionalMedicoRepository.persist(profesionalMedico);
	}

	@Override
	public void savePaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTurnoMedico(TurnoMedico turnoMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllProfesionalMedico() {
		profesionalMedicoRepository.listAll();
		
	}

	@Override
	public void getByIdProfesionalMedico(Long id) {
		//falta validar el null
		profesionalMedicoRepository.findById(id);
	}

	@Override
	public void deleteByIdProfesionalMedico(Long id) {
		//falta validar el null
		profesionalMedicoRepository.deleteById(id);
		
	}

	@Override
	public void saveAdministrador(Administrador admin) {
		administradorRepository.persist(admin);
		
	}

	@Override
	public void deleteByIdAdministrador(Long id) {
		//falta validar null
		administradorRepository.deleteById(id);
		
	}
}
