package com.luisu404.challengeliteratura.model;

public enum Idioma {

    EN("en", "Ingles"),
    ES("es", "Español"),
    FR("fr", "Frances"),
    PT("pt", "Portugués"),
    DE("de", "Aleman");

    private final String codigo;
    private final String nombreCompleto;

    Idioma(String codigo, String nombreCompleto) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public static String obtenerNombreCompleto(String codigo) {
        for (Idioma idioma : values()) {
            if (idioma.codigo.equalsIgnoreCase(codigo)) {
                return idioma.nombreCompleto;
            }
        }
        return codigo; // si no existe, devuelve el mismo
    }
}