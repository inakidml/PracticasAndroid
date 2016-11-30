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

    public int devolverPrimo(int i) {
        if (i < 3) {
            return i;
        } else {
            return calcularPrimo(i);
        }
    }

    private int calcularPrimo(int i) {

        int contador = primos[contadorPrimos] + 2; // empezamos a buscar a partir del siguinete impar almacenado
        int primo = -1;
        if (primos[i] != 0) {
            return primos[i];
        } else {
            while (contadorPrimos != i) {
                if (esPrimo(contador)) {// si nos devuelve true
                    contadorPrimos++; //sumamos uno al contador
                    primos[contadorPrimos] = contador; // lo guardamos
                    primo = contador; // lo devolvemos al main
                }
                contador += 2; // solo pasamos los impares
            }
        }
        return primo;
    }

    //devuelve true si un número es primo
    private Boolean esPrimo(int i) {
        Boolean esPrimo = false;
        int raizPrimo = (int) (Math.sqrt(i));
        if (i % 2 == 0) {
            return false;// si es par, no es primo
        } else {
            int contadorCriba = 3;
            // lo dividimos por todos los primos almacenados hasta la raiz del candidato, si el valor es 0 es que no hay mas almacenados
            while (primos[contadorCriba] <= raizPrimo && primos[contadorCriba] != 0) {
                if (i % primos[contadorCriba] == 0) {
                    return false;
                }
                contadorCriba++;
            }
            if (primos[contadorCriba]==0) {
                //si hemos salido del while por primos[contadorCriba]==0 debemos seguir probando
                //si no esta almacenado, seguimos dividiendo por todos los impares
                for (int j = primos[contadorCriba] + 2; j <= raizPrimo; j += 2) {
                    if (i % j == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
