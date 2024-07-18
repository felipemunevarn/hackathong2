package com.alura.back.Dtos;

import lombok.Data;


public class DtoTest {
    String mensaje = "esto es una prueba";
    int id = 1;

    public DtoTest(){

    }
/*
    public DtoTest(String mensaje, int id) {
        this.mensaje = mensaje;
        this.id = id;
    }
    */

    public int getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }
}
