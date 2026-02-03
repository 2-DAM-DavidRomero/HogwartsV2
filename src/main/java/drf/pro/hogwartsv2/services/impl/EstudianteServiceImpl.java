package drf.pro.hogwartsv2.services.impl;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.mappers.EstudianteMapper;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.repositories.EstudianteRepository;
import drf.pro.hogwartsv2.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService{
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;


    @Override
    public EstudianteDTO crearUsuario(EstudianteCreateDTO dto) {

        Estudiante estudiante = estudianteMapper.toEntity(dto);

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        return estudianteMapper.toDto(estudianteGuardado);
    }

    @Override
    public EstudianteDTO actualizarEstudiante(long id, EstudianteUpdateDTO dto) {
        Estudiante usuarioExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));

        estudianteMapper.updateEntityFromDto(dto, usuarioExistente);
        Estudiante usuarioActualizado = estudianteRepository.save(usuarioExistente);

        return estudianteMapper.toDto(usuarioActualizado);    }
}
