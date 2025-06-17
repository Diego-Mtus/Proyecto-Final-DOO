package org.udec;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GeneradorNombreAleatorio {

    private static final String RUTA = "/nombres_mascota.txt";

    public static String obtenerNombreAleatorio(){
        InputStream inputStream = GeneradorNombreAleatorio.class.getResourceAsStream(RUTA);

        if (inputStream == null) {
            System.err.println("Error: El archivo de nombres no se encuentra en los recursos: " + RUTA);
            return "Morgana";
        }

        List<String> nombres = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String linea;

            while ((linea = reader.readLine()) != null) {
                nombres.add(linea);
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo de nombres: " + e.getMessage());
            return "Morgana";
        }

        Random indiceAleatorio = new Random();
        return nombres.get(indiceAleatorio.nextInt(nombres.size()));

    }
}
