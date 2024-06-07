package org.group2.Service;

import java.util.List;
import org.group2.Model.Receta;
import org.group2.Model.TurnoMedico;

public interface IRecetaService {
	public void addReceta(Receta receta);
	public List<Receta> getRecetas();
	public void delReceta(Long id);
	public Receta findReceta(Long id);
	public String editReceta(Long id, String medicamentos, String diagnostico, TurnoMedico turnoMedico);
}