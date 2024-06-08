package org.group2.Service;

import java.util.List;
import java.util.Optional;

import org.group2.Model.Paciente;

public interface IPacienteService {
	public void addPaciente(Paciente paciente);
	public List<Paciente> getPacientes();
	public void delPaciente(Long id);
	public Optional<Paciente> findPaciente(Long id);
	public String editPaciente(Long id, Paciente paciente);
}
