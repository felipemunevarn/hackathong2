package com.alura.back.services.implementService;

import com.alura.back.Dtos.responseDto.DeleteResponseDto;
import com.alura.back.Dtos.responseDto.InscripcionResponseDto;
import com.alura.back.entities.Equipo;
import com.alura.back.entities.Evento;
import com.alura.back.entities.Inscripcion;
import com.alura.back.entities.User;
import com.alura.back.mappers.InscripcionMapper;
import com.alura.back.repositories.EquipoRepository;
import com.alura.back.repositories.EventoRepository;
import com.alura.back.repositories.InscripcionRepository;
import com.alura.back.repositories.UserRepository;
import com.alura.back.services.interfaceService.IInscripcionService;
import com.alura.back.utils.Estado;
import com.alura.back.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autor leonardo vargas
 * clase que contiene la logica para crear una inscripcion,
 * devolver una lista de inscripto a un evento y
 * y editar la inscripcion
 */
@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements IInscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final UserRepository userRepository;
    private final EventoRepository eventoRepository;
    private final EquipoRepository equipoRepository;
    private final InscripcionMapper mapper = new InscripcionMapper();


    @Override
    @Transactional
    public InscripcionResponseDto crearInscripcion(Long eventoId, Long userId) {
        //siempre va a haber un equipo por defecto
        //Optional<Equipo> equipoDefault = equipoRepository.findById(1L);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado", HttpStatus.BAD_REQUEST));

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundException("evento no encontrado", HttpStatus.BAD_REQUEST));

        Inscripcion inscripcion = mapper.fromDataToInscripcion(user, null, evento);

        inscripcionRepository.save(inscripcion);

        return mapper.fromInscripcionToInscripcionResponseDto();
    }


    @Override
    public List<InscripcionResponseDto> ListarInscritos(Long eventoId ) {
        //List<Inscripcion> lista= inscripcionRepository.getAllInscripcionesEvento(eventoId);
        List<Inscripcion> lista= inscripcionRepository.getInscripcionesEvento(eventoId);
        if (lista.isEmpty()){
            throw new NotFoundException("No hay inscripciones asociadas al enevento id" , HttpStatus.BAD_REQUEST);
        }
       return mapper.fromListIncripcionToListInscripcionResponseDto(lista );
    }

    @Override
    public DeleteResponseDto darBajaInscripcion(Long inscripId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripId)
                .orElseThrow(() -> new NotFoundException("no se encontró inscrpción con el id enviado" , HttpStatus.BAD_REQUEST));

        // borrado logico de inscripcion
        inscripcion.setEstado(Estado.BAJA.name());

        inscripcionRepository.save(inscripcion);

        return new DeleteResponseDto(inscripcion.getInscripcion_id() ,  "Se ha dado de baja la inscripcion con exito");
    }

}


