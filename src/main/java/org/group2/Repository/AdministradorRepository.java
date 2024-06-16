package org.group2.Repository;

import org.group2.Model.Administrador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador>{
		
}
