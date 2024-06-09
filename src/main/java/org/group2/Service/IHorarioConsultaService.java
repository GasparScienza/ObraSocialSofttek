package org.group2.Service;

import java.util.List;

import org.group2.Model.HorarioConsulta;

public interface IHorarioConsultaService {
	public List<HorarioConsulta> getHorariosDisponibles(Long profesionMedicoId);
	public List<HorarioConsulta> getHorarios(Long profesionMedicoId);
	public void addHorarioConsulta(HorarioConsulta horarioConsulta);
	public void cancelHorarioConsulta(Long id);
}
