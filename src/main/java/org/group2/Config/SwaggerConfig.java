package org.group2.Config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
		info= @Info(
				title = "Obra Social API",
				version = "1.0.0",
				description= "Sistema de gestion de Turnos Medicos",
				contact = @Contact(
					name="Grupo2 (Beltran,Scienza,Escalera)",					
					email="facundocristianbeltran@gmail.com",
					url ="www.google.com"),	
				license = @License(
						name ="Apache ",
						url = "http://www.google.com"
						)
				)
		)
@ApplicationPath("/api")
public class SwaggerConfig extends Application{

}
