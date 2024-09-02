
package com.mycompany.qb;

/**
 *
 * @author Rangerson Clemente
 */

public class Qb {
    int[][] qb_atual;
    int g;
    int heuristica;
    int f;
    Qb pai;

    public Qb(int[][] qb_atual, int g, int heuristica, Qb pai) {
        this.qb_atual = qb_atual;
        this.g = g;
        this.heuristica = heuristica;
        this.f = g + heuristica;
        this.pai = pai;
    }
}
