package org.group2.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.group2.Model.Paciente;
import org.group2.Model.ProfesionalMedico;
import org.group2.Model.TurnoMedico;
import org.group2.Repository.TurnoMedicoRepository;
import org.group2.Service.ITurnoMedicoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TurnoMedicoService implements ITurnoMedicoService{

	@Inject
	private TurnoMedicoRepository turnoMedicoRepository;
	@Override
	public void addTurno(TurnoMedico turnoMedico) {
		turnoMedicoRepository.persist(turnoMedico);
	}

	@Override
	public List<TurnoMedico> getTurnos() {
		return turnoMedicoRepository.listAll();
	}

	@Override
	public void delTurno(Long id) {
		turnoMedicoRepository.deleteById(id);
	}

	@Override
	public TurnoMedico findTurno(Long id) {
		return turnoMedicoRepository.findById(id);
	}

	@Override
	public String editTurno(Long id, Paciente paciente, ProfesionalMedico profesionalMedico, LocalDateTime fechaHora, String motivoConsulta) {
		try {
			if(id != null) {
				TurnoMedico turnoMedico = this.findTurno(id);
				if(turnoMedico == null) {
					throw new RuntimeException("El turno medico no existe.");
				}
				if(fechaHora != null) {
					turnoMedico.setFechaHora(fechaHora);
				}
				if(motivoConsulta != null) {
					turnoMedico.setMotivoConsulta(motivoConsulta);
				}
				if(paciente != null) {
					turnoMedico.setPaciente(paciente);
				}
				if(profesionalMedico != null) {
					turnoMedico.setProfesional(profesionalMedico);
				}
				turnoMedicoRepository.getEntityManager().merge(turnoMedico);
				return "Turno medico editado exitosamente.";
			}else {
				throw new RuntimeException("El ID del turno no puede ser nulo.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

}
