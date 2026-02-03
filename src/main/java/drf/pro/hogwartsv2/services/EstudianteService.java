package drf.pro.hogwartsv2.services;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;

public interface EstudianteService {
    EstudianteDTO crearUsuario(EstudianteCreateDTO dto);
    EstudianteDTO actualizarEstudiante(long id, EstudianteUpdateDTO dto);
}