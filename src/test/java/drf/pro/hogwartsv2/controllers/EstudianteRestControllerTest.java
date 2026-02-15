package drf.pro.hogwartsv2.controllers;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.create.MascotaCreateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.services.EstudianteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteRestController.class)
public class EstudianteRestControllerTest {

    @Autowired
    private MockMvc mockMvc; // Para realizar peticiones HTTP

    @MockitoBean
    private EstudianteService estudianteService; // Moqueamos el servicio para no necesitar base de datos

    @Autowired
    private ObjectMapper objectMapper;

    private EstudianteCreateDTO estudianteCreateDTO;
    private EstudianteDTO estudianteResponseDTO;

    @BeforeEach
    void setUp() {
        // Preparamos el DTO de Mascota (necesario para la validación del request)
        MascotaCreateDTO mascotaDTO = new MascotaCreateDTO();
        mascotaDTO.setNombre("Hedwig");
        mascotaDTO.setEspecie("Búho");

        // Preparamos el Estudiante Create DTO (request principal)
        estudianteCreateDTO = new EstudianteCreateDTO();
        estudianteCreateDTO.setNombre("Harry");
        estudianteCreateDTO.setApellido("Potter");
        estudianteCreateDTO.setAnyoCurso(1);
        estudianteCreateDTO.setFechaNacimiento(LocalDate.of(1980, 7, 31));
        estudianteCreateDTO.setMascota(mascotaDTO);

        // Respuesta simulada (lo que devuelve el servicio)
        estudianteResponseDTO = new EstudianteDTO();
        estudianteResponseDTO.setId(1L);
        estudianteResponseDTO.setNombre("Harry");
    }

    @Test
    void crearEstudiante_Exito() throws Exception {
        // GIVEN
        when(estudianteService.crearUsuario(any(EstudianteCreateDTO.class))).thenReturn(estudianteResponseDTO);

        // WHEN & THEN
        mockMvc.perform(post("/api/estudiante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudianteCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Harry"));

        verify(estudianteService).crearUsuario(any(EstudianteCreateDTO.class));
    }

    @Test
    void crearEstudiante_AnyoInvalido() throws Exception {
        // GIVEN: El año 10 no es válido (suponiendo validación @Max(7) en el DTO)
        estudianteCreateDTO.setAnyoCurso(10);

        // WHEN & THEN
        mockMvc.perform(post("/api/estudiante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudianteCreateDTO)))
                .andExpect(status().isBadRequest());

        // El servicio nunca debe ser llamado si los DTOs fallan la validación
        verify(estudianteService, never()).crearUsuario(any());
    }

    @Test
    void eliminarEstudiante_DebeRetornar204() throws Exception {
        // GIVEN: Simulamos éxito en la eliminación
        doNothing().when(estudianteService).eliminarUsuario(1L);

        // WHEN & THEN
        mockMvc.perform(delete("/api/estudiante/1"))
                .andExpect(status().isNoContent()); // Verifica 204 No Content

        verify(estudianteService).eliminarUsuario(1L);
    }
}