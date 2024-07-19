package com.alura.back.Dtos.responseDto;

import java.time.LocalDate;

/**
 * Auto leonardo vargas
 * objeto que se devuelve despues de crear una inscripción
 */
public record InscripcionResponseDto( Long inscripcionId,
                                      String nombreInscrito,
                                      LocalDate fechaInscrion,
                                      String nombreEvento,
                                      String estado) { }