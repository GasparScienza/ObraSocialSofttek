package org.group2.Service;

import java.util.List;
import org.group2.DTO.ProfesionalMedicoDTO;
import org.group2.Model.ProfesionalMedico;

//medicos, dar de baja los turnos y demas.
public interface IProfesionalMedicoService {
	public void saveProfesionalMedico(ProfesionalMedico profesionalMedico);
	public ProfesionalMedico getByIdProfesionalMedico(Long id);
	public List<ProfesionalMedico> getAllProfesionalMedico();
	public void deleteByIdProfesionalMedico(Long id);
	public String updateByIdProfesionalMedico(Long id, ProfesionalMedico profesionalMedico);
	public List<ProfesionalMedicoDTO> cartillaMedico();
}
