package org.group2.Repository;

import java.util.List;

import org.group2.Model.HorarioConsulta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HorarioConsultaRepository implements PanacheRepository<HorarioConsulta> {
    public List<HorarioConsulta> findHorariosDisponiblesByProfesionMedicoId(Long profesionMedicoId) {
        return list("profesionalMedico.id = ?1 and disponibilidad = true", profesionMedicoId);
    }
    public List<HorarioConsulta> findHorariosByProfesionMedicoId(Long profesionMedicoId) {
        return list("profesionalMedico.id", profesionMedicoId);
    }
}
