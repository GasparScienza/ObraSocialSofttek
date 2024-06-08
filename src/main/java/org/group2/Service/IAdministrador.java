package org.group2.Service;

import org.group2.Model.Administrador;

//el admin va a poder sacar turnos para cualquier paciente, 
//ademas de que va a poder crear pacientes, 
public interface IAdministrador  {
	//ADMIN
	public void saveAdministrador(Administrador admin);
	public String updateAdministrador(Long id, Administrador administrador);
	
	public void deleteByIdAdministrador(Long id);
	public Administrador findByIdAdministrador(Long id);
}
