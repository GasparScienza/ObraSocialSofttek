package org.group2.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.group2.Model.Paciente;
import org.group2.Model.UserLogin;
import org.group2.Repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class PacienteServiceTest {

    @InjectMock
    private PacienteRepository pacienteRepository;
    @Inject
    private PacienteService pacienteService;
    @Mock
    private UserLogin user1;
    private UserLogin user2;
    private List<Paciente> pacientes;

    @BeforeEach
    void setUp(){
        Set<String> rol = Collections.singleton("PACIENTE");
        user1 = new UserLogin("raul", "1234", rol);
        user2 = new UserLogin("juan", "1234", rol);
        Paciente paciente1 = new Paciente(1L, "raul", "raul@mail.com", "231231231", user1);
        Paciente paciente2 = new Paciente(2L, "juan", "juan@mail.com", "231231231", user2);
        pacientes = new ArrayList<>();
        pacientes.add(paciente1);
        pacientes.add(paciente2);
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
    void testAddPaciente() {

    }

    @Test
    void testDelPaciente() {

    }

    @Test
    void testEditPaciente() {

    }

    @Test
    void testFindPaciente() {

    }

    
}
