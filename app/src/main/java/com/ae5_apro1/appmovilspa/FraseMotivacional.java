package com.ae5_apro1.appmovilspa;

public class FraseMotivacional {
    private final String frase;
    private final String palabra_clave;

    public FraseMotivacional(String frase, String palabra_clave) {
        this.frase = frase;
        this.palabra_clave = palabra_clave;
    }

    public String getFrase() {
        return frase;
    }

    public String getPalabraClave() {
        return palabra_clave;
    }
}
