package com.diaz.inaki.practica1;

import java.util.List;

/**
 * Created by 8fdi02 on 24/11/16.
 */


public class CalculoPrimos {
    int[] primos = new int[1000001];
    int contadorPrimos;


    public CalculoPrimos() {
        primos[0] = 0;
        primos[1] = 1;
        primos[2] = 2;
        primos[3] = 3;
        contadorPrimos = 3;
    }

    public int devolverPrimo(int i) {// devuelve el primo de la posición que se pasa como argumento
        if (i < 3) {
            return i;
        } else {
            return calcularPrimo(i);//a partir del tres llamamos a la función calcular el primo de la posición i
        }
    }

    private int calcularPrimo(int i) { // recibimos como parametro la posición que queremos

        int contador = primos[contadorPrimos] + 2; // empezamos a buscar a partir del siguiente impar almacenado
        int primo = -1;
        if (primos[i] != 0) { // si la posición solicitada contiene un número diferente de 0 es que ya lo hemos calculado y lo devolvemos directamente
            return primos[i];
        } else {
            while (contadorPrimos != i) { // hacemos un bucle hasta que la posición solicitada sea igual al número de primos que tenemos
                if (esPrimo(contador)) {// si nos devuelve true contador es primo
                    contadorPrimos++; //sumamos uno al contadorPrimos (posición)
                    primos[contadorPrimos] = contador; // guardamos el primo en la posición
                    primo = contador; // lo cargamos en primo para devolverlo al main cuando si salimos del bucle
                }
                contador += 2; // solo pasamos los impares
            }
        }
        return primo;
    }

    //devuelve true si un número es primo
    private Boolean esPrimo(int i) {
        Boolean esPrimo = false; // variable para devolver
        int raizPrimo = (int) (Math.sqrt(i)); // raiz del candidato
        int contadorCriba = 3;
        // lo dividimos por todos los primos almacenados hasta la raiz del candidato
        while (primos[contadorCriba] <= raizPrimo) {
            if (i % primos[contadorCriba] == 0) {
                return false;
            }
            contadorCriba++;
        }
        return true;
    }
}
