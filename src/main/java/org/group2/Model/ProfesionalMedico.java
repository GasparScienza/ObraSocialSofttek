package org.group2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfesionalMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombreProfesional;
	private String emailProfesional;
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	//private HorarioConsulta horarioConsulta;
	private String ubicacionConsulta;
	//private UserLogin userLogin;
	//TODO user login ProfesionalMedico y joins columns
	
}
