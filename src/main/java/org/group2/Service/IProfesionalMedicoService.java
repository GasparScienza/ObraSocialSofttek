package org.group2.Service;

import org.group2.Model.ProfesionalMedico;

public interface IProfesionalMedicoService {
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico);
	public void getAll();
	public void getById(Long id);
	public void deleteById(Long id);
	
	
}
