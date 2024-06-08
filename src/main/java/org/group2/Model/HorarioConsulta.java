package org.group2.Model;

import org.group2.Enums.DiaEnum;
import org.group2.Enums.HorarioConsultaEnum;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioConsulta extends PanacheEntity {
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public DiaEnum dia;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "horario", nullable = false)
	public HorarioConsultaEnum horario;
	
}
