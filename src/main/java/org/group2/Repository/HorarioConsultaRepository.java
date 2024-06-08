package org.group2.Repository;

import org.group2.Model.HorarioConsulta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HorarioConsultaRepository implements PanacheRepository<HorarioConsulta> {

}
