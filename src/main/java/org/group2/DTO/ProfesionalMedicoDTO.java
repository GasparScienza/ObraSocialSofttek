package org.group2.DTO;

import java.util.List;
import org.group2.Enums.Especialidad;
import org.group2.Model.ProfesionalMedico;
import org.group2.Service.IHorarioConsultaService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.inject.Inject;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfesionalMedicoDTO {

	@Inject @JsonIgnore
	private IHorarioConsultaService horarioConsultaService;

	private Long id;
	private String nombreProfesional;
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	private List<HorarioConsultaDTO> horarioConsulta;
	private String ubicacionConsulta;

	public ProfesionalMedicoDTO(ProfesionalMedico profesionalMedico) {
		this.nombreProfesional = profesionalMedico.getNombreProfesional();
		this.especialidad = profesionalMedico.getEspecialidad();
		this.horarioConsulta = horarioConsultaService.getHorarios(profesionalMedico.getId());
		this.ubicacionConsulta = profesionalMedico.getUbicacionConsulta();
		this.id=profesionalMedico.getId();
	}
}
