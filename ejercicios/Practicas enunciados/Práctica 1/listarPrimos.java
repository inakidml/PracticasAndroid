package com.jaureguialzo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Instante inicial (en milisengundos)
        long inicio = System.currentTimeMillis();

        // Pedir el número
        System.out.print("Introduce un número: ");
        int maximo = Integer.parseInt(br.readLine());

        // Generar la secuencia de 1 a maximo
        int j = 2;

        while (j <= maximo) {

            // Comprobar si j es primo
            int i = 2;

            while (i < (j / 2) + 1 && j % i != 0) {
                i++;
            }

            // Ver por qué ha parado el bucle
            if (!(i < (j / 2) + 1)) {
                System.out.println(j);
            }

            j++;
        }

        // Instante final
        long fin = System.currentTimeMillis();

        // Tiempo transcurrido
        System.out.println("Tiempo: " + (fin - inicio) / 1000.0);
    }
}