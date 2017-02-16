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
    private final int PRINCIPIANTE[] = {8, 8, 10, 64}; //alto, ancho, número de minas, num casillas
    private final int AMATEUR[] = {12, 12, 30, 144};


    private final int AVANZADO[] = {16, 16, 60, 256};

    //para posiciones aleatorias
    private List<Integer> posicionMinas = new ArrayList<>();

    private int[][] posicionEnArray = new int[256][2]; // posicion en el array 0 - j, 1 - k
    private int[][] arrayEnPosicion = new int[16][16];
    //tableros
    private int tableroPrincipìante[][] = new int[8][8];
    private int tableroAmateur[][] = new int[12][12];
    private int tableroAvanzado[][] = new int[16][16];

    // casilla con 0 vacia, con nueve mina, 1-8, número de minas vecinas
    public static int vacia = 0;
    public static int mina = 9;

    //constructor al que le pasamos el nivel
    public Tablero(int nivel) {
        rellenarTablero(nivel);

    }
    //getters
    public int[] getAVANZADO() {
        return AVANZADO;
    }

    public int[] getPRINCIPIANTE() {
        return PRINCIPIANTE;
    }

    public int[] getAMATEUR() {
        return AMATEUR;
    }

    public int[][] getTableroPrincipìante() {
        return tableroPrincipìante;
    }

    public int[][] getTableroAmateur() {
        return tableroAmateur;
    }

    public int[][] getTableroAvanzado() {
        return tableroAvanzado;
    }

    public int[][] getPosicionEnArray() {
        return posicionEnArray;
    }

    public int[][] getArrayEnPosicion() {
        return arrayEnPosicion;
    }

    public List<Integer> getPosicionMinas() {
        return posicionMinas;
    }

    //Metodos
    private void mostrarTablero(int[][] tablero) { //mostrar el tablero en terminal - en debug
        for (int j = 0; j < tablero.length; j++) {
            for (int k = 0; k < tablero.length; k++) {
                System.out.print(tablero[j][k]);
            }
            System.out.println();
        }
    }

    private void algoritmoRelleno(int[][] tablero) {//rellena el tablero con las minas y adyacentes
        int contador = 0; //contador a 0 para saber la posición de las minas
        for (int j = 0; j < tablero.length; j++) { // recorremos el array y vamos poniendo las minas y sus vecinas
            for (int k = 0; k < tablero.length; k++) {
                posicionEnArray[contador][0] = j;
                posicionEnArray[contador][1] = k;
                arrayEnPosicion[j][k] = contador;
                if (posicionMinas.contains(contador)) {
                    tablero[j][k] = mina;
                    //
                    if (j == 0 && k > 0 && k < tablero.length - 1) { // primera fila
                        if (tablero[j][k + 1] != 9) { // el de delante
                            tablero[j][k + 1]++;
                        }
                        if (tablero[j][k - 1] != 9) { // el de detrás
                            tablero[j][k - 1]++;
                        }
                        if (tablero[j + 1][k] != 9) { // el de debajo
                            tablero[j + 1][k]++;
                        }
                        if (tablero[j + 1][k - 1] != 9) { // el de debajo detrás
                            tablero[j + 1][k - 1]++;
                        }
                        if (tablero[j + 1][k + 1] != 9) { // el de debajo delante
                            tablero[j + 1][k + 1]++;
                        }
                    }
                    if (j == tablero.length - 1 && k > 0 && k < tablero.length - 1) {  // última fila
                        if (tablero[j][k + 1] != 9) { // el de delante
                            tablero[j][k + 1]++;
                        }
                        if (tablero[j][k - 1] != 9) { // el de detrás
                            tablero[j][k - 1]++;
                        }
                        if (tablero[j - 1][k - 1] != 9) { // el de arriba detras
                            tablero[j - 1][k - 1]++;
                        }
                        if (tablero[j - 1][k] != 9) {  // el de arriba
                            tablero[j - 1][k]++;
                        }
                        if (tablero[j - 1][k + 1] != 9) { // el de arriba delante
                            tablero[j - 1][k + 1]++;
                        }
                    }
                    if (j == 0 && k == 0) { // primera casilla primera fila
                        if (tablero[j][k + 1] != 9) { // el de delante
                            tablero[j][k + 1]++;
                        }
                        if (tablero[j + 1][k] != 9) { // el de debajo
                            tablero[j + 1][k]++;
                        }
                        if (tablero[j + 1][k + 1] != 9) { // el de debajo delante
                            tablero[j + 1][k + 1]++;
                        }
                    }
                    if (j == 0 && k == tablero.length - 1) { //ultima casilla primera fila
                        if (tablero[j][k - 1] != 9) { // el de detrás
                            tablero[j][k - 1]++;
                        }
                        if (tablero[j + 1][k - 1] != 9) { // el de debajo detrás
                            tablero[j + 1][k - 1]++;
                        }
                        if (tablero[j + 1][k] != 9) { // el de debajo
                            tablero[j + 1][k]++;
                        }

                    }

                    if (j == tablero.length - 1 && k == 0) { // primera casilla última fila
                        if (tablero[j][k + 1] != 9) { // el de delante
                            tablero[j][k + 1]++;
                        }
                        if (tablero[j - 1][k] != 9) {  // el de arriba
                            tablero[j - 1][k]++;
                        }
                        if (tablero[j - 1][k + 1] != 9) { // el de arriba delante
                            tablero[j - 1][k + 1]++;
                        }
                    }
                    if (j == tablero.length - 1 && k == tablero.length - 1) { //ultima casilla última fila
                        if (tablero[j][k - 1] != 9) { // el de detrás
                            tablero[j][k - 1]++;
                        }

                        if (tablero[j - 1][k - 1] != 9) { // el de arriba detras
                            tablero[j - 1][k - 1]++;
                        }
                        if (tablero[j - 1][k] != 9) {  // el de arriba
                            tablero[j - 1][k]++;
                        }
                    }
                    if (k == 0 && j > 0 && j < tablero.length - 1) { // primera columna
                        if (tablero[j][k + 1] != 9) { // el de delante
                            tablero[j][k + 1]++;
                        }

                        if (tablero[j + 1][k] != 9) { // el de debajo
                            tablero[j + 1][k]++;
                        }

                        if (tablero[j + 1][k + 1] != 9) { // el de debajo delante
                            tablero[j + 1][k + 1]++;
                        }
                        if (tablero[j - 1][k + 1] != 9) { // el de arriba delante
                            tablero[j - 1][k + 1]++;
                        }
                        if (tablero[j - 1][k] != 9) {  // el de arriba
                            tablero[j - 1][k]++;
                        }
                    }
                    if (k == tablero.length - 1 && j > 0 && j < tablero.length - 1) { // última columna

                        if (tablero[j + 1][k] != 9) { // el de debajo
                            tablero[j + 1][k]++;
                        }

                        if (tablero[j - 1][k] != 9) {  // el de arriba
                            tablero[j - 1][k]++;
                        }
                        if (tablero[j][k - 1] != 9) { // el de detrás
                            tablero[j][k - 1]++;
                        }
                        if (tablero[j - 1][k - 1] != 9) { // el de arriba detras
                            tablero[j - 1][k - 1]++;
                        }
                        if (tablero[j + 1][k - 1] != 9) { // el de debajo detrás
                            tablero[j + 1][k - 1]++;
                        }
                    }

                    if (j > 0 && j < tablero.length - 1 && k > 0 && k < tablero.length - 1) { //casillas centrales
                        if (tablero[j][k + 1] != 9) { // el de delante
                            tablero[j][k + 1]++;
                        }
                        if (tablero[j][k - 1] != 9) { // el de detrás
                            tablero[j][k - 1]++;
                        }
                        if (tablero[j - 1][k - 1] != 9) { // el de arriba detras
                            tablero[j - 1][k - 1]++;
                        }
                        if (tablero[j - 1][k] != 9) {  // el de arriba
                            tablero[j - 1][k]++;
                        }
                        if (tablero[j - 1][k + 1] != 9) { // el de arriba delante
                            tablero[j - 1][k + 1]++;
                        }
                        if (tablero[j + 1][k] != 9) { // el de debajo
                            tablero[j + 1][k]++;
                        }
                        if (tablero[j + 1][k - 1] != 9) { // el de debajo detrás
                            tablero[j + 1][k - 1]++;
                        }
                        if (tablero[j + 1][k + 1] != 9) { // el de debajo delante
                            tablero[j + 1][k + 1]++;
                        }
                    }
                }
                contador++;
            }
        }
        if (MainActivity.DEBUG) {
            imprimirPosiciones();
        }
        tableroLineal(tablero);
    }

    private void tableroLineal(int[][] tablero) { // pasa el tablero de array bidimensional a arraylist
        posicionMinas.clear();
        for (int j = 0; j < tablero.length; j++) { // recorremos el array y vamos poniendo las minas
            for (int k = 0; k < tablero.length; k++) {
                posicionMinas.add(tablero[j][k]);
            }
        }

    }

    private void imprimirPosiciones() { // muestra en que posiciones irán las minas por terminal - debug

        for (int posicion : posicionMinas) { // recorremos el array de posiciones para mostrarlo
            System.out.println("posición mina: " + posicion);

        }
    }

    private void contarMinas(int[][] tablero) { //contador minas - debug
        int contador = 0;
        for (int j = 0; j < tablero.length; j++) {
            for (int k = 0; k < tablero.length; k++) {
                if (tablero[j][k] == 9) {
                    contador++;
                }
            }

        }
        System.out.println("Número de minas= " + contador);

    }

    private void rellenarTablero(int nivel) {//pasandole el nivel, nos rellena un arraylist con las posiciones aleatorias de las minas y despues crea el tablero en un array

        Random r = new Random();
        int contador = 0;
        switch (nivel) {

            case 1: //principiante
                while (contador < PRINCIPIANTE[2]) { //rellenamos un arraylist con las posiciones
                    int i = r.nextInt(PRINCIPIANTE[3]); //un num aleatorio con máximo de minas
                    if (!posicionMinas.contains(i)) { // si ya existe en al arraylist no lo añadimos
                        posicionMinas.add(i);
                        contador++;
                    }
                }
                Collections.sort(posicionMinas); //ordenamos el arraylist
                algoritmoRelleno(tableroPrincipìante);
                if (MainActivity.DEBUG) {
                    mostrarTablero(tableroPrincipìante);
                    contarMinas(tableroPrincipìante);
                }
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

                algoritmoRelleno(tableroAmateur);
                if (MainActivity.DEBUG) {
                    mostrarTablero(tableroAmateur);
                    contarMinas(tableroAmateur);
                }
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

                algoritmoRelleno(tableroAvanzado);

                if (MainActivity.DEBUG) {
                    mostrarTablero(tableroAvanzado);
                    contarMinas(tableroAvanzado);
                }


                break;


            default:
                System.out.println("Nivel erroneo");
        }

    }

}
