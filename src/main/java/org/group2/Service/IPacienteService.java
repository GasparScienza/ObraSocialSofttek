package org.group2.Service;

import java.util.List;

import org.group2.Model.Paciente;

public interface IPacienteService {
	public void addPaciente(Paciente paciente);
	public List<Paciente> getPacientes();
	public void delPaciente(Long id);
	public Paciente findPaciente(Long id);
	public String editPaciente(Long id, Paciente paciente);
}
