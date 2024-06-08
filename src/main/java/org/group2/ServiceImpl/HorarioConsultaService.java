package org.group2.ServiceImpl;

import java.util.List;

import org.group2.Model.HorarioConsulta;
import org.group2.Repository.HorarioConsultaRepository;
import org.group2.Service.IHorarioConsultaService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HorarioConsultaService implements IHorarioConsultaService{
	@Inject
	public HorarioConsultaRepository horarioConsultaRepository;

	@Override
	public void addHorarioConsulta(HorarioConsulta horarioConsulta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HorarioConsulta> getHorarios() {
		// TODO Auto-generated method stub
		return horarioConsultaRepository.listAll();
	}

	@Override
	public void cancelHorarioConsulta(Long id) {
		// TODO Auto-generated method stub
		
	}
}
