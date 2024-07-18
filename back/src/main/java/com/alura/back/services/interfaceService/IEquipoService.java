package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.entities.Equipo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.List;

public interface IEquipoService {

    public EquipoResponseDto GererarEquipos(Long user_Id);


}
