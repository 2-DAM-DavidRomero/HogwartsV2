package drf.pro.hogwartsv2.repositories;

import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.models.Mascota;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Testcontainers // Habilita la gestión automática de contenedores
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"}) // Hibérnate genera las tablas en el contenedor
public class EstudiantesRepositoriesTest {

    // Configuración del contenedor PostgreSQL
    @Container
    @ServiceConnection // Conecta automáticamente las propiedades de conexión (URL, user, pass)
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17.6-alpine");

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void eliminarUsuario_DebeEliminarPerfilEnCascada_RealDB() {
        // GIVEN: Datos completos para cumplir con las restricciones NOT NULL
        Estudiante usuario = new Estudiante();
        usuario.setNombre("Harry");
        usuario.setApellido("Potter");


        Mascota mascota = new Mascota();
        mascota.setNombre("AAAA");

        usuario.setMascota(mascota);
        mascota.setEstudiante(usuario);


        Estudiante guardado = estudianteRepository.save(usuario);
        Long idPerfil = guardado.getMascota().getIdMacota();

        // WHEN
        estudianteRepository.delete(guardado);
        estudianteRepository.flush(); // Fuerza la ejecución del DELETE en Postgres
        entityManager.clear();     // Limpia la caché para asegurar consulta a disco

        // THEN: Verificación JPA-Friendly
        assertFalse(estudianteRepository.findById(guardado.getIdEstudiante()).isPresent());

        // Verificamos que el perfil desapareció de la tabla real
        Mascota perfilEnBD = entityManager.find(Mascota.class, idPerfil);
        assertNull(perfilEnBD, "El perfil debería haber sido borrado por la cascada en Postgres");
    }
}
