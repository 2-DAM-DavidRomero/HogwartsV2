package drf.pro.hogwartsv2.services;

import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.repositories.EstudianteRepository;
import drf.pro.hogwartsv2.services.impl.EstudianteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class) // Inicializa Mockito sin cargar Spring [cite: 557]
public class EstudiantesServiceTest {

    @Mock // Crea un objeto simulado UsuarioRepository vacío
    private EstudianteRepository estudianteRepository;

    @InjectMocks // Crea la instancia del servicio e inyecta los mocks dentro
    private EstudianteServiceImpl estudianteService;

    private Estudiante estudianteTest;

    @BeforeEach
    void setUp() {
        estudianteTest = new Estudiante();
        estudianteTest.setIdEstudiante(1L);
        estudianteTest.setNombre("Harry");
        estudianteTest.setApellido("Potter");
    }

    @Test
    void expulsarEstudiantesConExito() {
        // GIVEN (Preparación)
        Long id = 1L;
        // Simulamos que el usuario existe y su lista de cuentas está vacía
        when(estudianteRepository.findById(id)).thenReturn(Optional.of(estudianteTest));

        // WHEN (Ejecución)
        estudianteService.eliminarUsuario(id);

        // THEN (Verificación)
        // Verificamos que se llamó al método delete del repositorio exactamente una vez
        verify(estudianteRepository, times(1)).delete(estudianteTest);
    }
}
