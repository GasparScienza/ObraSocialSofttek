package org.group2.Model;

import org.group2.Enums.DiaEnum;
import org.group2.Enums.HorarioConsultaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private DiaEnum dia;
	
	@Enumerated(EnumType.STRING)
	private HorarioConsultaEnum horario;
 
	@ManyToOne
    @JoinColumn(name = "profesional_medico_id")
    private ProfesionalMedico profesionalMedico;

	private Boolean disponibilidad;
}
