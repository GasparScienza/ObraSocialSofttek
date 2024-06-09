package org.group2.ServiceImpl;

import java.util.List;
import org.group2.Model.Administrador;
import org.group2.Repository.AdministradorRepository;
import org.group2.Service.IAdministrador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AdministradorService implements IAdministrador{
	
	@Inject
	AdministradorRepository administradorRepository;


	@Override
	public void saveAdministrador(Administrador admin) {
		administradorRepository.persist(admin);
		
	}

	@Override
	public void deleteByIdAdministrador(Long id) {
		
		administradorRepository.deleteById(id);
		
	}

	@Override
	public String updateAdministrador(Long id, Administrador administrador) {
		try {
			if(id != null) {
				Administrador a = this.findByIdAdministrador(id);
				if(a == null) {
					throw new RuntimeException("El paciente no existe.");
				}
				if(administrador.getNombreYApellido()!= null) {
					a.setNombreYApellido(administrador.getNombreYApellido());
				}
				if(administrador.getCuil()!= null) {
					a.setCuil(administrador.getCuil());
				}
				administradorRepository.getEntityManager().merge(a);
				return "Administrador editado exitosamente.";
			}else {
				throw new RuntimeException("El ID del Administrador no puede ser nulo.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	@Override
	public Administrador findByIdAdministrador(Long id) {
		
		return administradorRepository.findById(id);		
	}

	@Override
	public List<Administrador> getAllAdmins() {
		
		return administradorRepository.listAll();
	}
	
}
