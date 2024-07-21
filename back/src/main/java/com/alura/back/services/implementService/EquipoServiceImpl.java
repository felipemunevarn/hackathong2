package com.alura.back.services.implementService;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.Dtos.responseDto.EquipoResultadoResponseDto;
import com.alura.back.entities.Equipo;
import com.alura.back.entities.Evento;
import com.alura.back.entities.Inscripcion;
import com.alura.back.exceptions.EquipoErrorExcetion;
import com.alura.back.exceptions.NotFoundException;
import  com.alura.back.mappers.EquipoMapper;
import com.alura.back.repositories.EquipoRepository;
import com.alura.back.repositories.EventoRepository;
import com.alura.back.repositories.InscripcionRepository;
import com.alura.back.services.interfaceService.IEquipoService;
import com.alura.back.utils.Estado;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * autor: Leonardo Vargas
 * email: leonardovargasfp@gmail.com
 * decripción: clase que implementa los metodos de la inteface IEquipoService,
 * para gestionar la logica de negocio relacionada a la entidad equipo
 */

@Service
@RequiredArgsConstructor
@Transactional
public class EquipoServiceImpl implements IEquipoService {

    private final EquipoRepository equipoRepository;
    private final EventoRepository eventoRepository;
    private final InscripcionRepository inscripcionRepository;

    @Override
    public EquipoResultadoResponseDto GererarEquipos(Long eventoId) {

        Evento evento = eventoRepository.findById(eventoId)
                                        .orElseThrow(() -> new NotFoundException("evento no encontrado", HttpStatus.BAD_REQUEST));

        GererarEquipos(evento.getParticipantes());

        return new EquipoResultadoResponseDto("equipos generados con exito");
    }

    @Override
    public List<EquipoResponseDto> ListarEquipos( Long eventoId) {
        List<Equipo> equiposEvento = equipoRepository.getListaEquipoevento(eventoId);

        if (equiposEvento.isEmpty()){
            throw new NotFoundException("No hay equipos asociads al enevento id" , HttpStatus.BAD_REQUEST);
        }

        return EquipoMapper.fromlistEquipoEventoToEquipoResponseDto(equiposEvento);
    }


    //metodo que genera los equipos aleatoriamente
    @Transactional
    private void GererarEquipos (List<Inscripcion> participantes) {
        System.out.println("entro al methodo cantidad de participantes: " + participantes.size());
        int maxIntegrantes = participantes.get(0).getEvento().getMaxIntegrantesPorEquipo();

        if(participantes.size() < maxIntegrantes){
            throw new EquipoErrorExcetion("no hay participantes suficientes para generar equipos" , HttpStatus.BAD_REQUEST);
        }

        List<Inscripcion> frontendDevelopers = new ArrayList<>();
        List<Inscripcion> backendDevelopers = new ArrayList<>();

        for (Inscripcion inscp : participantes) {
            if ("Front-End".equalsIgnoreCase(inscp.getUser().getDevType())) {
                frontendDevelopers.add(inscp);
            } else if ("Back-End".equalsIgnoreCase(inscp.getUser().getDevType())) {
                backendDevelopers.add(inscp);
            }
        }

        System.out.println("dividio los integrantes" + "front: " +  frontendDevelopers.size()
                + "back: " +  backendDevelopers.size());

        int cont = 1;

        while (frontendDevelopers.size() >= maxIntegrantes/2 && backendDevelopers.size() >= maxIntegrantes/2) {
            System.out.println("ento al while");

            Equipo nuevoEquipo = new Equipo();
            List<Inscripcion> integrantes = new ArrayList<>();

            nuevoEquipo.setEstado(Estado.COMPLETO.name());
            nuevoEquipo.setNombre("equipo " + cont);
            nuevoEquipo.setFechaCreacion(LocalDate.now());

            for (int i = 0; i < maxIntegrantes/2; i++) {
                integrantes.add(backendDevelopers.remove(0));
                integrantes.add(frontendDevelopers.remove(0));
            }


            for(Inscripcion inscrip : integrantes){
                inscrip.setEstado(Estado.INSCRITO.name());
                inscrip.setEquipo(nuevoEquipo);

            }
            System.out.println("llego al guardado");
            nuevoEquipo.setParticipantes(integrantes);

            System.out.println("guardooooooooo");
            equipoRepository.save(nuevoEquipo);




            cont ++;
        }
    }

}
