package org.group2.Service;

import java.util.List;

import org.group2.DTO.HorarioConsultaDTO;
import org.group2.Model.HorarioConsulta;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


public interface IHorarioConsultaService {
	public List<HorarioConsulta> getHorariosDisponibles(Long profesionMedicoId);
	public List<HorarioConsultaDTO> getHorarios(Long profesionMedicoId);
	public void addHorarioConsulta(HorarioConsulta horarioConsulta);
	public void cancelHorarioConsulta(Long id);
}
