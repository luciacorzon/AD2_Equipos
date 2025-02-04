package org.example;

public enum Posicion {
    BASE("G"),
    ESCOLTA("G"),
    ALERO("F"),
    ALA_PIVOT("F-C"),
    PIVOT("C");

    private final String code;

    Posicion(String code) {
        this.code = code;
    }

    public String toCode() {
        return code;
    }

    public static Posicion fromCode(String code) {
        for (Posicion posicion : values()) {
            if (posicion.code.equals(code)) {
                return posicion;
            }
        }
        throw new IllegalArgumentException("Código de posición inválido: " + code);
    }
}
