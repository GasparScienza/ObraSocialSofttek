package org.group2.Service;

import java.util.List;

import org.group2.Model.HorarioConsulta;

public interface IHorarioConsultaService {

	public void addHorarioConsulta(HorarioConsulta horarioConsulta);
	public List<HorarioConsulta> getHorarios();
	public void cancelHorarioConsulta(Long id);
}
