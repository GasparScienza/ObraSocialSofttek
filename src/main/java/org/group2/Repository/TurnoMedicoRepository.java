package org.group2.Repository;

import org.group2.Model.TurnoMedico;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TurnoMedicoRepository implements PanacheRepository<TurnoMedico>{

}
