package drf.pro.hogwartsv2.services;

import drf.pro.hogwartsv2.dtos.response.CasaDTO;

import java.util.List;

public interface CasaSerivice {
    List<CasaDTO> obtenerTodasLasCasa();
}
