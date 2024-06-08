package org.group2.Service;

import org.group2.Model.ProfesionalMedico;
import org.group2.Model.Receta;
import org.group2.Model.TurnoMedico;


//medicos, dar de baja los turnos y demas.

public interface IProfesionalMedicoService {
	
	public void cancelarTurno(Long IdTurnoMedico);
	public void generarReceta(Receta receta);
	
	
}
