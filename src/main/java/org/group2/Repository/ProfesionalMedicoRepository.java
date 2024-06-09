package org.group2.Repository;

import java.util.List;
import org.group2.Model.ProfesionalMedico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfesionalMedicoRepository implements PanacheRepository<ProfesionalMedico>{
    public List<ProfesionalMedico> findProfesionalesConHorariosDisponibles() {
        return getEntityManager().createQuery(
                "SELECT DISTINCT pm FROM ProfesionalMedico pm JOIN HorarioConsulta hc ON pm.id = hc.profesionalMedico.id WHERE hc.disponibilidad = true", 
                ProfesionalMedico.class)
            .getResultList();
    }
}
