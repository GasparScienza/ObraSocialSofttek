package org.group2.ServiceImpl;

import java.util.List;

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
	public List<HorarioConsulta> getHorarios(Long profesionMedicoId) {
		return horarioConsultaRepository.findHorariosByProfesionMedicoId(profesionMedicoId);
	}

	@Override
	public void cancelHorarioConsulta(Long id) {
	}

	@Override
	public List<HorarioConsulta> getHorariosDisponibles(Long profesionMedicoId) {
		return horarioConsultaRepository.findHorariosDisponiblesByProfesionMedicoId(profesionMedicoId);
	}
}
