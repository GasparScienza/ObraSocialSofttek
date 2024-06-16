package org.group2.ServiceImpl;

import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.group2.Enums.DiaEnum;
import org.group2.Enums.Especialidad;
import org.group2.Enums.HorarioConsultaEnum;
import org.group2.Model.HorarioConsulta;
import org.group2.Model.ProfesionalMedico;
import org.group2.Model.UserLogin;
import org.group2.Repository.HorarioConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import jakarta.inject.Inject;

@QuarkusTest
public class HorarioConsultaServiceTest {
    
    @InjectMock
    private HorarioConsultaRepository horarioConsultaRepository;
    @Inject
    private HorarioConsultaService horarioConsultaService;
    @Mock
    private ProfesionalMedico profesionalMedico;
    @Mock
    private UserLogin user;
    @Mock
    private HorarioConsulta horario;
    @Mock
    List<HorarioConsulta> esperado;

    @BeforeEach
    void setUp(){
        esperado = new ArrayList<>();
        Set<String> rol = Collections.singleton("PROFESIONAL");
        user = new UserLogin("pedro", "1234", rol);
        profesionalMedico = new ProfesionalMedico(1L, "Pedro", "pedro@mail.com", Especialidad.ODONTOLOGIA, "Cordoba", user);
        horario = new HorarioConsulta(1L, DiaEnum.LUNES, HorarioConsultaEnum.H08_00_08_30, profesionalMedico, true);
        esperado.add(horario);
    }

    @Test
    void testAddHorarioConsulta(){
        // Configurar el mock para que no haga nada al persistir
        Mockito.doNothing().when(horarioConsultaRepository).persist(
            ArgumentMatchers.any(HorarioConsulta.class)
        );
        // Llamar al método bajo prueba
        horarioConsultaService.addHorarioConsulta(horario);
        // Verificar que el método persist fue llamado
        verify(horarioConsultaRepository).persist(horario);
    }

    @Test
    void getHorariosDisponibles(){
        Mockito.when(horarioConsultaRepository.findHorariosDisponiblesByProfesionMedicoId(1L))
                .thenReturn(esperado);
        List<HorarioConsulta> resultado = horarioConsultaService.getHorariosDisponibles(1L);
        assertEquals(esperado, resultado);
        verify(horarioConsultaRepository).findHorariosDisponiblesByProfesionMedicoId(1L);
    }

}
