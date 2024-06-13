package org.group2.DTO;

import java.util.List;

import org.group2.Enums.DiaEnum;
import org.group2.Enums.Especialidad;
import org.group2.Enums.HorarioConsultaEnum;
import org.group2.Model.HorarioConsulta;
import org.group2.Model.ProfesionalMedico;
import org.group2.Service.IHorarioConsultaService;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HorarioConsultaDTO {
	
	@Enumerated(EnumType.STRING)
	private DiaEnum dia;
	
	@Enumerated(EnumType.STRING)
	private HorarioConsultaEnum horario;
    
	private Boolean disponibilidad;

	public HorarioConsultaDTO(HorarioConsulta horaC) {
		
		this.dia = horaC.getDia();
		this.horario = horaC.getHorario();
		this.disponibilidad = horaC.getDisponibilidad();
	}
	
	
	
}
