package drf.pro.hogwartsv2.services.impl;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.mappers.EstudianteMapper;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.repositories.EstudianteRepository;
import drf.pro.hogwartsv2.services.EstudianteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService{
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;


    @Override
    @Transactional
    public EstudianteDTO crearUsuario(EstudianteCreateDTO dto) {

        Estudiante estudiante = estudianteMapper.toEntity(dto);

        estudiante.getMascota().setEstudiante(estudiante);

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        return estudianteMapper.toDto(estudianteGuardado);
    }

    @Override
    public EstudianteDTO actualizarEstudiante(long id, EstudianteUpdateDTO dto) {
        Estudiante usuarioExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));

        estudianteMapper.updateEntityFromDto(dto, usuarioExistente);
        Estudiante usuarioActualizado = estudianteRepository.save(usuarioExistente);

        return estudianteMapper.toDto(usuarioActualizado);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));


        // El perfil asociado se eliminará automáticamente debido a CascadeType.ALL y orphanRemoval = true
        estudianteRepository.delete(estudiante);
    }
}
