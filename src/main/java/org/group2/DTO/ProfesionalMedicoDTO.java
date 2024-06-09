package org.group2.DTO;

import org.group2.Model.Especialidad;

import org.group2.Model.ProfesionalMedico;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfesionalMedicoDTO {
	private String nombreProfesional;
	private String emailProfesional;
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	//private HorarioConsulta horarioConsulta;
	private String ubicacionConsulta;
	//private UserLogin userLogin;
	
	
	public ProfesionalMedicoDTO(ProfesionalMedico profesionalMedico) {
		this.nombreProfesional=profesionalMedico.getNombreProfesional();
		this.emailProfesional=profesionalMedico.getEmailProfesional();
		this.especialidad=profesionalMedico.getEspecialidad();
		//this.horarioConsulta=profesionalMedico.getHorarioConsulta();
		this.ubicacionConsulta=profesionalMedico.getUbicacionConsulta();
	
		// TODO falta definir userlogindto
		/*if(profesionalMedico.getUserLogin()!=null) {
			this.userLogin= new UserLoginDTO()
		}
		*/
	}
	
	
	
	
}
