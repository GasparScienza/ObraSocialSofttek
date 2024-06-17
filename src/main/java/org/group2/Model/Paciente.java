package org.group2.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
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
@ApiModel(value = "Paciente", description ="define a los pacientes")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes="id incremental")
	private Long id;
	@ApiModelProperty(notes="nombre y apellido del paciente", example="Juan Perez",required=true)
	private String nombre;
	@ApiModelProperty(notes="email del paciente", example="Juan@gmail.com",required=true)
	private String email;
	@ApiModelProperty(notes="Numero de telefono del paciente", example="1143563822",required=true)
	private String telefono;
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserLogin user;
}
