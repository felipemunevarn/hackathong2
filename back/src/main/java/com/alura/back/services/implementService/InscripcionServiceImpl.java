package com.alura.back.services.implementService;

import com.alura.back.Dtos.responseDto.InscripcionResponseDto;
import com.alura.back.entities.Evento;
import com.alura.back.entities.Inscripcion;
import com.alura.back.entities.User;
import com.alura.back.repositories.EventoRepository;
import com.alura.back.repositories.InscripcionRepository;
import com.alura.back.repositories.UserRepository;
import com.alura.back.services.interfaceService.IInscripcionService;
import com.alura.back.utils.EstadoInscripcion;
import com.alura.back.exceptions.MiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements IInscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final UserRepository userRepository;
    private final EventoRepository eventoRepository;

    @Override
    public InscripcionResponseDto crearInscripcion(Long eventoId , Long userId)  {
        Inscripcion inscrip = new Inscripcion();

        User user = userRepository.findById(userId).orElseThrow(() -> new MiException("Usuario no encontrado"));
        Evento evento = eventoRepository.findById(eventoId).orElseThrow(() -> new MiException("evento no encontrado"));


        inscrip.setFechaInscripcion(LocalDate.now());
        inscrip.setEstado(EstadoInscripcion.ESPERA.name());
        inscrip.setUser(user);
        inscrip.setEvento(evento);

        inscripcionRepository.save(inscrip);

        return new InscripcionResponseDto(inscrip.getInscripcion_Id(),
                user.getFirstName() + " " + user.getLastName(),
                             inscrip.getFechaInscripcion(),
                             evento.getNombre(),
                             EstadoInscripcion.ESPERA.name());



    }
}
