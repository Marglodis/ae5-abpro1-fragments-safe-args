package com.ae5_apro1.appmovilspa;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {
    private Context context;

    public JsonLoader(Context context) {
        this.context = context;
    }

    public List<FraseMotivacional> cargarFrases() {
        List<FraseMotivacional> frases = new ArrayList<>();

        try {
            // Abrir el InputStream del archivo JSON que esta en raw
            InputStream inputStream = context.getResources().openRawResource(R.raw.frases);

            // Envolverlo en un InptStreamReader y uego en un Bufferreader para lectura eficinete
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                stringBuilder.append(linea).append("\n");
            }
            reader.close();
            inputStream.close();

            // Convertir el StringBuilder a una cadena y parsear el JSON
            String jsonString = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String frase = jsonObject.getString("frase");
                String palabraClave = jsonObject.getString("palabra_clave");
                frases.add(new FraseMotivacional(frase, palabraClave));
            }
        } catch (IOException | JSONException e) {
            Log.e("JsonLoader", "Error al cargar el archivo JSON", e);

        }
        return frases;
    }
}
