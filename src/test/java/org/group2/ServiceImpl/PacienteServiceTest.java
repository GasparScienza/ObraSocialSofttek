package org.group2.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.group2.Model.Paciente;
import org.group2.Model.UserLogin;
import org.group2.Repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@QuarkusTest
public class PacienteServiceTest {

    @InjectMock
    private PacienteRepository pacienteRepository;
    @Inject
    private PacienteService pacienteService;
    @Mock
    private UserLogin user1;
    private UserLogin user2;
    private UserLogin user3;
    private List<Paciente> pacientes;
    private Paciente paciente1;
    private Paciente paciente2;
    private Paciente pacienteEditado;
    @Mock
    private EntityManager entityManager;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        Set<String> rol = Collections.singleton("PACIENTE");
        user1 = new UserLogin("raul", "1234", rol);
        user2 = new UserLogin("juan", "1234", rol);
        user3 = new UserLogin("vene", "1234", rol);
        paciente1 = new Paciente(1L, "raul", "raul@mail.com", "231231231", user1);
        paciente2 = new Paciente(2L, "juan", "juan@mail.com", "231231231", user2);
        pacienteEditado = new Paciente(1L, "venera", "vene@mail.com", "231231231", user3);
        pacientes = new ArrayList<>();
        pacientes.add(paciente1);
        pacientes.add(paciente2);
        when(pacienteRepository.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void testGetPacientes() {
        Mockito.when(pacienteRepository.listAll()).thenReturn(pacientes);
        List<Paciente> resultado = pacienteService.getPacientes();
        assertEquals(resultado, pacientes);
        assertNotNull(resultado);
        verify(pacienteRepository).listAll();
    }

    @Test
    void testEditPaciente() {
        Mockito.when(pacienteRepository.findByIdOptional(1L)).thenReturn(Optional.of(paciente1));
        Mockito.when(entityManager.merge(any(Paciente.class))).thenReturn(paciente1);
        String resultado = pacienteService.editPaciente(1L, pacienteEditado);
        // Verifica que el método findByIdOptional fue llamado con el ID correcto
        verify(pacienteRepository).findByIdOptional(1L);
        // Verifica que el método merge fue llamado con el paciente correcto
        verify(entityManager).merge(paciente1);
        assertEquals("Paciente editado exitosamente.", resultado);
        assertEquals(pacienteEditado.getNombre(), paciente1.getNombre());
        assertEquals(pacienteEditado.getEmail(), paciente1.getEmail());
        assertEquals(pacienteEditado.getTelefono(), paciente1.getTelefono());
        assertEquals(pacienteEditado.getUser(), paciente1.getUser());
    }   
}
