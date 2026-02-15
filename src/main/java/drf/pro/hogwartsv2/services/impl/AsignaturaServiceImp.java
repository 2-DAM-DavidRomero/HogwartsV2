package drf.pro.hogwartsv2.services.impl;

import drf.pro.hogwartsv2.mappers.EstudianteMapper;
import drf.pro.hogwartsv2.models.Asignatura;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.repositories.AsignaturaRepository;
import drf.pro.hogwartsv2.repositories.EstudianteRepository;
import drf.pro.hogwartsv2.services.AsignaturaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImp implements AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;

    @Override
    public void eliminarAsignatura(Long id) {
        Asignatura estudiante = asignaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrado con id: " + id));


        // El perfil asociado se eliminará automáticamente debido a CascadeType.ALL y orphanRemoval = true
        asignaturaRepository.delete(estudiante);
    }
}
