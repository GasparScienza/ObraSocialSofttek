package org.group2.Model;



import org.group2.Enums.Especialidad;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

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
@ApiModel(value = "ProfesionalMedico", description = "define los medicos")
public class ProfesionalMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes="id incremental")
	private Long id;
	@ApiModelProperty(notes="nombre medico", example = "jorge lanata", required=true)
	private String nombreProfesional;
	@ApiModelProperty
	private String emailProfesional;
	@Enumerated(EnumType.STRING)
	@ApiModelProperty
	private Especialidad especialidad;
	@ApiModelProperty
	private String ubicacionConsulta;
	@OneToOne
	@JoinColumn(name="user_id")
	private UserLogin userLogin;
	
	
}
