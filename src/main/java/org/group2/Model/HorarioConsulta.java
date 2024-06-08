package org.group2.Model;

import org.group2.Enums.DiaEnum;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class HorarioConsulta extends PanacheEntity {
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public DiaEnum dia;
	
	@ManyToOne
	@JoinColumn(name = "horario_id", nullable = false)
	public Horario horario;
}
