package org.group2.Repository;

import java.util.Optional;
import org.group2.Model.Receta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaRepository implements PanacheRepository<Receta>{
	//Comparar id de turno medico con el id asociado al objeto receta
	public Optional<Receta> findByTurnoMedicoId(Long turnoMedicoId) {
	    return find("turnoMedico.id", turnoMedicoId).firstResultOptional();
	}
}
