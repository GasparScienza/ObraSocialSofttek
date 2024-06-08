package org.group2.DTO;

import org.group2.Model.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteDTO {
	private String nombre;
	private String email;
	private String telefono;
	private UserLoginDTO user;
	
	public PacienteDTO(Paciente paciente) {
		this.nombre = paciente.getNombre();
		this.email = paciente.getEmail();
		this.telefono = paciente.getTelefono();
		if(paciente.getUser() != null) {
			this.user = new UserLoginDTO(paciente.getUser().getUsername(),paciente.getUser().getRol());
		}
		
	}
	
	
}
