package org.group2.Service;

import org.group2.Model.Administrador;
import org.group2.Model.Paciente;
import org.group2.Model.ProfesionalMedico;
import org.group2.Model.TurnoMedico;

//el admin va a poder sacar turnos para cualquier paciente, 
//ademas de que va a poder crear pacientes, 
public interface IAdministrador  {
	//ADMIN
	public void saveAdministrador(Administrador admin);
	public void deleteByIdAdministrador(Long id);
	//PROFESIONAL MEDICO
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico);
	public void getByIdProfesionalMedico(Long id);
	public void getAllProfesionalMedico();
	public void deleteByIdProfesionalMedico(Long id);
	
	//PACIENTE
	public void savePaciente(Paciente paciente);
	//TURNO MEDICO
	public void createTurnoMedico(TurnoMedico turnoMedico);
	
}
