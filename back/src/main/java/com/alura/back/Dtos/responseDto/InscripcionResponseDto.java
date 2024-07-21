package com.alura.back.Dtos.responseDto;

import java.time.LocalDate;

/**
 * Autor: leonardo vargas
 * email: leonardovargasfp@gmail.com
 * descripción: objeto que se devuelve despues de crear una inscripción
 */
public record InscripcionResponseDto( Long inscripcionId,
                                      String nombreInscrito,
                                      String especialidad,
                                      LocalDate fechaInscrion,
                                      String nombreEvento,
                                      String estado) { }