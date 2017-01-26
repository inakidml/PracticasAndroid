package com.diaz.inaki.buscaminas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by 8fdi02 on 26/1/17.
 */

public class Tablero {

    //NIVELES DEL JUEGO

    final int PRINCIPIANTE[] = {8, 8, 10, 64}; //alto, ancho, número de minas, num casillas
    final int AMATEUR[] = {12, 12, 30, 144};
    final int AVANZADO[] = {16, 16, 60, 256};

    List<Integer> posicionMinas = new ArrayList<>();

    int tableroPrincipìante[][] = new int[8][8];
    int tableroAmateur[][] = new int[12][12];
    int tableroAvanzado[][] = new int[12][12];

    static int vacia = 0;
    static int mina = 9;

    public Tablero(int nivel) {
        rellenarTablero(nivel);

    }

    private void mostrarTablero(int[][] tablero) {
        for (int j = 0; j < tablero.length; j++) {
            for (int k = 0; k < tablero.length; k++) {
                System.out.print(tablero[j][k]);
            }
            System.out.println();
        }
    }

    private void rellenarTablero(int nivel) {

        Random r = new Random();
        int contador = 0;
        switch (nivel) {

            case 1: //principiante
                while (contador < PRINCIPIANTE[2]) { //rellenamos un arraylist con las posiciones
                    int i = r.nextInt(PRINCIPIANTE[3]);
                    if (!posicionMinas.contains(i)) {
                        posicionMinas.add(i);
                        contador++;
                    }
                }
                Collections.sort(posicionMinas); //ordenamos el arraylist

                contador = 0; //contador a 0 para saber la posición de las minas
                for (int j = 0; j < tableroPrincipìante.length; j++) { // recorremos el array y vamos poniendo las minas
                    for (int k = 0; k < tableroPrincipìante.length; k++) {
                        if (posicionMinas.contains(contador)) {
                            tableroPrincipìante[j][k] = mina;
                            //
                            if (j == 0 && k > 0 && k < tableroPrincipìante.length - 1) { // primera fila
                                if (tableroPrincipìante[j][k + 1] != 9) { // el de delante
                                    tableroPrincipìante[j][k + 1]++;
                                }
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k] != 9) { // el de debajo
                                    tableroPrincipìante[j + 1][k]++;
                                }
                                if (tableroPrincipìante[j + 1][k - 1] != 9) { // el de debajo detrás
                                    tableroPrincipìante[j + 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k + 1] != 9) { // el de debajo delante
                                    tableroPrincipìante[j + 1][k + 1]++;
                                }
                            }
                            if (j == tableroPrincipìante.length - 1 && k > 0 && k < tableroPrincipìante.length - 1) {  // última fila
                                if (tableroPrincipìante[j][k + 1] != 9) { // el de delante
                                    tableroPrincipìante[j][k + 1]++;
                                }
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k - 1] != 9) { // el de arriba detras
                                    tableroPrincipìante[j - 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k] != 9) {  // el de arriba
                                    tableroPrincipìante[j - 1][k]++;
                                }
                                if (tableroPrincipìante[j - 1][k + 1] != 9) { // el de arriba delante
                                    tableroPrincipìante[j - 1][k + 1]++;
                                }
                            }
                            if (j == 0 && k == 0) { // primera casilla primera fila
                                if (tableroPrincipìante[j][k + 1] != 9) { // el de delante
                                    tableroPrincipìante[j][k + 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k] != 9) { // el de debajo
                                    tableroPrincipìante[j + 1][k]++;
                                }
                                if (tableroPrincipìante[j + 1][k + 1] != 9) { // el de debajo delante
                                    tableroPrincipìante[j + 1][k + 1]++;
                                }
                            }
                            if (j == 0 && k == tableroPrincipìante.length - 1) { //ultima casilla primera fila
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k - 1] != 9) { // el de debajo detrás
                                    tableroPrincipìante[j + 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k] != 9) { // el de debajo
                                    tableroPrincipìante[j + 1][k]++;
                                }

                            }


                            if (j == tableroPrincipìante.length - 1 && k == 0) { // primera casilla última fila
                                if (tableroPrincipìante[j][k + 1] != 9) { // el de delante
                                    tableroPrincipìante[j][k + 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k] != 9) {  // el de arriba
                                    tableroPrincipìante[j - 1][k]++;
                                }
                                if (tableroPrincipìante[j - 1][k + 1] != 9) { // el de arriba delante
                                    tableroPrincipìante[j - 1][k + 1]++;
                                }
                            }

                            if (j == tableroPrincipìante.length - 1 && k == tableroPrincipìante.length - 1) { //ultima casilla última fila
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k - 1] != 9) { // el de arriba detras
                                    tableroPrincipìante[j - 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k] != 9) {  // el de arriba
                                    tableroPrincipìante[j - 1][k]++;
                                }

                            }

                            if (k == 0 && j > 0 && j < tableroPrincipìante.length - 1) { // primera columna
                                if (tableroPrincipìante[j][k + 1] != 9) { // el de delante
                                    tableroPrincipìante[j][k + 1]++;
                                }

                                if (tableroPrincipìante[j + 1][k] != 9) { // el de debajo
                                    tableroPrincipìante[j + 1][k]++;
                                }

                                if (tableroPrincipìante[j + 1][k + 1] != 9) { // el de debajo delante
                                    tableroPrincipìante[j + 1][k + 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k + 1] != 9) { // el de arriba delante
                                    tableroPrincipìante[j - 1][k + 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k] != 9) {  // el de arriba
                                    tableroPrincipìante[j - 1][k]++;
                                }
                            }

                            if (k == tableroPrincipìante.length - 1 && j > 0 && j < tableroPrincipìante.length - 1) { // última columna

                                if (tableroPrincipìante[j + 1][k] != 9) { // el de debajo
                                    tableroPrincipìante[j + 1][k]++;
                                }

                                if (tableroPrincipìante[j - 1][k] != 9) {  // el de arriba
                                    tableroPrincipìante[j - 1][k]++;
                                }
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k - 1] != 9) { // el de arriba detras
                                    tableroPrincipìante[j - 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k - 1] != 9) { // el de debajo detrás
                                    tableroPrincipìante[j + 1][k - 1]++;
                                }
                            }


                            if (j > 0 && j < tableroPrincipìante.length - 1 && k > 0 && k < tableroPrincipìante.length - 1) { //casillas centrales
                                if (tableroPrincipìante[j][k + 1] != 9) { // el de delante
                                    tableroPrincipìante[j][k + 1]++;
                                }
                                if (tableroPrincipìante[j][k - 1] != 9) { // el de detrás
                                    tableroPrincipìante[j][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k - 1] != 9) { // el de arriba detras
                                    tableroPrincipìante[j - 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j - 1][k] != 9) {  // el de arriba
                                    tableroPrincipìante[j - 1][k]++;
                                }
                                if (tableroPrincipìante[j - 1][k + 1] != 9) { // el de arriba delante
                                    tableroPrincipìante[j - 1][k + 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k] != 9) { // el de debajo
                                    tableroPrincipìante[j + 1][k]++;
                                }
                                if (tableroPrincipìante[j + 1][k - 1] != 9) { // el de debajo detrás
                                    tableroPrincipìante[j + 1][k - 1]++;
                                }
                                if (tableroPrincipìante[j + 1][k + 1] != 9) { // el de debajo delante
                                    tableroPrincipìante[j + 1][k + 1]++;
                                }

                            }

                        }
                        contador++;

                    }
                }

                for (int posicion : posicionMinas) { // recorremos el array de posiciones para mostrarlo
                    System.out.println("posición mina: " + posicion);

                }

                mostrarTablero(tableroPrincipìante);

                break;

            case 2: // amateur
                while (contador < AMATEUR[2]) {
                    int i = r.nextInt(AMATEUR[3]);
                    if (!posicionMinas.contains(i)) {
                        posicionMinas.add(i);
                        contador++;
                    }
                }
                Collections.sort(posicionMinas);
                /*
                for (int posicion : posicionMinas) { // recorremos el array de posiciones para mostrarlo
                    System.out.println("posición mina: " + posicion);

                }
                */
                break;

            case 3: //avanzado
                while (contador < AVANZADO[2]) {
                    int i = r.nextInt(AVANZADO[3]);
                    if (!posicionMinas.contains(i)) {
                        posicionMinas.add(i);
                        contador++;
                    }
                }
                Collections.sort(posicionMinas);
                /*
                for (int posicion : posicionMinas) { // recorremos el array de posiciones para mostrarlo
                    System.out.println("posición mina: " + posicion);

                }
                */
                break;


            default:
                System.out.println("Nivel erroneo");
        }

    }

}
