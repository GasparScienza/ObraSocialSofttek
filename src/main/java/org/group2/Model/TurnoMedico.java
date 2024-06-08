package org.group2.Model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TurnoMedico{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*@ManyToOne
	@JoinColumn(name = "profesional_id")
	private ProfesionalMedico profesional;*/
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	/*LocalDateTime fechaHoraDesdeTexto = LocalDateTime.parse("2024-06-06T15:30:45");
	// La cadena debe estar en el formato "yyyy-MM-dd'T'HH:mm:ss"*/
	private LocalDateTime fechaHora;
	private String motivoConsulta;
}
