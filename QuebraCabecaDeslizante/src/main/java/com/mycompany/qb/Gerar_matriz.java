
package com.mycompany.qb;

/**
 *
 * @author Rangerson Clemente
 */
import java.util.Random;
import java.util.ArrayList;

/*
 * @author Rangerson Clemente
 */
public class Gerar_matriz {
    int qb[][] = new int[3][3];
    ArrayList<Integer> numerosGerados = new ArrayList<>();

    public int[][] gerarMatriz() {
        boolean valorRepetido = true;
        int numero, l, c;

        for (l = 0; l < 3; l++) {
            for (c = 0; c < 3; c++) {
                while (valorRepetido) {
                    numero = escolherNumero();
                    if (!numerosGerados.contains(numero)) {
                        valorRepetido = false;
                        numerosGerados.add(numero);
                        qb[l][c] = numero;
                    }
                }
                valorRepetido = true;
            }
        }

        return qb;
    }

    public int escolherNumero() {
        Random alet = new Random();
        return alet.nextInt(9);
    }
}