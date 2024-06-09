package org.group2.Model;

import org.group2.Enums.Especialidad;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	@OneToMany
	@JoinColumn(name="horarioConsulta_id")
	private HorarioConsulta horarioConsulta;
	private String ubicacionConsulta;
	@OneToOne
	@JoinColumn(name="user_id")
	private UserLogin userLogin;
	
	
}
