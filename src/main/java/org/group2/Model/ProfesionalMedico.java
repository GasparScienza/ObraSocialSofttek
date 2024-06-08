package org.group2.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProfesionalMedico{
	
	private String nombre;
}
