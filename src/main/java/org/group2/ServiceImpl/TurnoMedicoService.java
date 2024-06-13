package org.group2.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import org.group2.Model.TurnoMedico;
import org.group2.Repository.TurnoMedicoRepository;
import org.group2.Service.ITurnoMedicoService;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TurnoMedicoService implements ITurnoMedicoService{

	@Inject
	private TurnoMedicoRepository turnoMedicoRepository;
	@Inject
    private SecurityIdentity securityIdentity;

	@Override
	public void addTurno(TurnoMedico turnoMedico) {
		turnoMedicoRepository.persist(turnoMedico);
	}

	@Override
	public List<TurnoMedico> getTurnos() {
		return turnoMedicoRepository.listAll();
	}

	public boolean eliminarTurno(TurnoMedico turno){
		LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraTurno = turno.getFechaHora();
		return fechaHoraTurno.isAfter(fechaHoraActual);
	}

	@Override
	public String delTurno(Long id) {
		TurnoMedico turno = this.findTurno(id);
		/*if(turno != null && securityIdentity.getRoles().contains("ADMIN")){
			if(eliminarTurno(turno)){
				turnoMedicoRepository.deleteById(id);
				return "Turno eliminado correctamente";
			}
		}*/
		if(turno != null && turno.getPaciente() != null && turno.getPaciente().getUser() != null &&
			turno.getPaciente().getUser().getUsername().equals(securityIdentity.getPrincipal().getName())){
			if(eliminarTurno(turno)){
				turnoMedicoRepository.deleteById(id);
				return "Turno eliminado correctamente";
			}
			return "No se puede eliminar el turno";
		}else {
			return "No está autorizado para eliminar el turno";
	    }
	}

	@Override
	public TurnoMedico findTurno(Long id) {
		return turnoMedicoRepository.findById(id);
	}

	@Override
	public String editTurno(Long id, TurnoMedico turno) {
		try {
			if(id != null) {
				TurnoMedico turnoMedico = this.findTurno(id);
				if(turnoMedico == null) {
					throw new RuntimeException("El turno medico no existe.");
				}
				if(turno.getFechaHora() != null) {
					turnoMedico.setFechaHora(turno.getFechaHora());
				}
				if(turno.getMotivoConsulta() != null) {
					turnoMedico.setMotivoConsulta(turno.getMotivoConsulta());
				}
				if(turno.getProfesional() != null) {
					turnoMedico.setProfesional(turno.getProfesional());
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
