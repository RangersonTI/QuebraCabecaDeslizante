
package com.mycompany.qb;

/**
 * @author Rangerson Clemente
 */
public class QuebraCabecaDeslizante {

    public static void main(String[] args) {
        ResolverQuebraCabeca newQb = new ResolverQuebraCabeca();
            
        Gerar_matriz matriz = new Gerar_matriz();
        
        int[][] quebraCabeca = matriz.gerarMatriz();

        Qb inicial = new Qb(quebraCabeca, 0, newQb.calcularHeuristica(quebraCabeca), null);

        newQb.resolverQuebraCabeca(inicial);
    }
}