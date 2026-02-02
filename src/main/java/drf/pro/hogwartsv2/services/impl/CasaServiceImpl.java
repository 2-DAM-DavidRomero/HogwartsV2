package drf.pro.hogwartsv2.services.impl;


import drf.pro.hogwartsv2.dtos.response.CasaDTO;
import drf.pro.hogwartsv2.mappers.CasaMapper;
import drf.pro.hogwartsv2.repositories.CasaRepository;
import drf.pro.hogwartsv2.services.CasaSerivice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CasaServiceImpl implements CasaSerivice {

    private final CasaRepository casaRepository;
    private final CasaMapper casaMapper;

    @Override
    public List<CasaDTO> obtenerTodasLasCasa() {
        return casaRepository.findAll()
                .stream()
                .map(casaMapper::toDto)
                .toList();
    }
}
