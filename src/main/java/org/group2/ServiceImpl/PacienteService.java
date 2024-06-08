package org.group2.ServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.group2.Model.Paciente;
import org.group2.Repository.PacienteRepository;
import org.group2.Service.IPacienteService;
import org.group2.Service.IUserLoginService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PacienteService implements IPacienteService{

	@Inject
	private PacienteRepository pacienteRepository;
	@Inject
	private IUserLoginService userLoginService;
	
	@Override
	public void addPaciente(Paciente paciente) {
		Set<String> rol = Collections.singleton("PACIENTE");
		paciente.setUser(userLoginService.defaultUser(paciente.getEmail(), paciente.getNombre(), rol));
		pacienteRepository.persist(paciente);
	}

	@Override
	public List<Paciente> getPacientes() {
		return pacienteRepository.listAll();
	}

	@Override
	public void delPaciente(Long id) {
		pacienteRepository.deleteById(id);
	}

	@Override
	public Paciente findPaciente(Long id) {
		return pacienteRepository.findById(id);
	}

	@Override
	public String editPaciente(Long id, Paciente paciente) {
		try {
			if(id != null) {
				Paciente p = this.findPaciente(id);
				if(p == null) {
					throw new RuntimeException("El paciente no existe.");
				}
				if(paciente.getEmail() != null) {
					p.setEmail(paciente.getEmail());
				}
				if(paciente.getNombre() != null) {
					p.setNombre(paciente.getNombre());
				}
				if(paciente.getTelefono() != null) {
					p.setTelefono(paciente.getTelefono());
				}
				if(paciente.getUser() != null) {
					p.setUser(paciente.getUser());
				}
				pacienteRepository.getEntityManager().merge(p);
				return "Receta editado exitosamente.";
			}else {
				throw new RuntimeException("El ID del paciente no puede ser nulo.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
