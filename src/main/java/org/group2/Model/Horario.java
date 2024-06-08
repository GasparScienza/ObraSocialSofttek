package org.group2.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Horario extends PanacheEntity {
	
	@Column(nullable = false)
	public String horaInicio;
	
	@Column(nullable = false)
	public String horaFin;
}
