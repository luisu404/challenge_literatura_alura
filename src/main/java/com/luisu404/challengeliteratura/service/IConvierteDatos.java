package com.luisu404.challengeliteratura.service;

public interface IConvierteDatos {
    <T> T convierteDatosLibroDeJsonAClase(String json, Class<T> clase);
}
