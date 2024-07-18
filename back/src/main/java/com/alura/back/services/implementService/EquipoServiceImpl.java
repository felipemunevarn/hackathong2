package com.alura.back.services.implementService;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.entities.Equipo;
import com.alura.back.repositories.EquipoRepository;
import com.alura.back.services.interfaceService.IEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements IEquipoService {

    private final EquipoRepository equipoRepository;

    @Override
    public EquipoResponseDto GererarEquipos(Long userId) {
        return null;
    }


}
