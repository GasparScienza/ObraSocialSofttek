package org.group2.DTO;

import org.group2.Enums.DiaEnum;
import org.group2.Enums.HorarioConsultaEnum;
import org.group2.Model.HorarioConsulta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HorarioConsultaDTO {
	
	private Long id;
	@Enumerated(EnumType.STRING)
	private DiaEnum dia;
	@Enumerated(EnumType.STRING)
	private HorarioConsultaEnum horario;
	private Boolean disponibilidad;

	public HorarioConsultaDTO(HorarioConsulta horaC) {
		this.id= horaC.getId();
		this.dia = horaC.getDia();
		this.horario = horaC.getHorario();
		this.disponibilidad = horaC.getDisponibilidad();
	}
}
