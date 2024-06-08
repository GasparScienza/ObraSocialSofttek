package org.group2.Service;

import java.util.List;
import org.group2.Model.TurnoMedico;

public interface ITurnoMedicoService {
	public void addTurno(TurnoMedico turnoMedico);
	public List<TurnoMedico> getTurnos();
	public void delTurno(Long id);
	public TurnoMedico findTurno(Long id);
	public String editTurno(Long id, TurnoMedico turno);
}
