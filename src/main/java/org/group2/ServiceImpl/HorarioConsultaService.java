package org.group2.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.group2.DTO.HorarioConsultaDTO;
import org.group2.Model.HorarioConsulta;
import org.group2.Repository.HorarioConsultaRepository;
import org.group2.Service.IHorarioConsultaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class HorarioConsultaService implements IHorarioConsultaService{
	@Inject
	public HorarioConsultaRepository horarioConsultaRepository;

	@Override
	public void addHorarioConsulta(HorarioConsulta horarioConsulta) {
		horarioConsultaRepository.persist(horarioConsulta);
	}

	@Override
	public void cancelHorarioConsulta(Long id) {
	}

	@Override
	public List<HorarioConsulta> getHorariosDisponibles(Long profesionMedicoId) {
		return horarioConsultaRepository.findHorariosDisponiblesByProfesionMedicoId(profesionMedicoId);
	}
	
	@Override
	public List<HorarioConsultaDTO> getHorarios(Long profesionMedicoId) {
		List<HorarioConsulta> horarios = this.getHorariosDisponibles(profesionMedicoId);
		List<HorarioConsultaDTO> horariosDTO = new ArrayList<>();
		for (HorarioConsulta h: horarios) {
			HorarioConsultaDTO horarioDTO = new HorarioConsultaDTO();
			horarioDTO.setDia(h.getDia());
			horarioDTO.setHorario(h.getHorario());
			horarioDTO.setDisponibilidad(h.getDisponibilidad());
			horarioDTO.setId(h.getId());
			horariosDTO.add(horarioDTO);
		}
		return horariosDTO;
	}
}
