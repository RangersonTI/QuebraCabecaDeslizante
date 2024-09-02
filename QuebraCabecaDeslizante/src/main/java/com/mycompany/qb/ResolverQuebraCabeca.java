
package com.mycompany.qb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rangerson Clemente
 */
public class ResolverQuebraCabeca {
    public static int calcularHeuristica(int[][] qb_atual) {
        int h = 0;
        int valorEsperado = 1;
        for (int i = 0; i < qb_atual.length; i++) {
            for (int j = 0; j < qb_atual.length; j++) {
                if (qb_atual[i][j] != 0 && qb_atual[i][j] != valorEsperado) {
                    h++;
                }
                valorEsperado++;
            }
        }
        return h;
    }

    public static boolean eObjetivo(int[][] qb_atual) {
        int valorEsperado = 1;
        for (int i = 0; i < qb_atual.length; i++) {
            for (int j = 0; j < qb_atual.length; j++) {
                if (qb_atual[i][j] != 0 && qb_atual[i][j] != valorEsperado) {
                    return false;
                }
                valorEsperado++;
            }
        }
        return true;
    }

    public static int[] encontrarVazio(int[][] qb_atual) {
        for (int i = 0; i < qb_atual.length; i++) {
            for (int j = 0; j < qb_atual.length; j++) {
                if (qb_atual[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static List<Qb> gerarVizinhos(Qb noAtual) {
        List<Qb> vizinhos = new ArrayList<>();
        int[] posVazio = encontrarVazio(noAtual.qb_atual);
        int linhaVazia = posVazio[0];
        int colunaVazia = posVazio[1];
        int[][] movimentos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // Movimentos elemento: Cima, Baixo, Esquerda, Direita

        for (int[] movimento : movimentos) {
            int novoaLinha = linhaVazia + movimento[0];
            int novaColuna = colunaVazia + movimento[1];

            if (novoaLinha >= 0 && novoaLinha < 3 && novaColuna >= 0 && novaColuna < 3) {
                int[][] novoEstado = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    System.arraycopy(noAtual.qb_atual[i], 0, novoEstado[i], 0, 3);
                }

                novoEstado[linhaVazia][colunaVazia] = novoEstado[novoaLinha][novaColuna];
                novoEstado[novoaLinha][novaColuna] = 0;
                Qb vizinho = new Qb(novoEstado, noAtual.g + 1, calcularHeuristica(novoEstado), noAtual);
                vizinhos.add(vizinho);
            }
        }

        return vizinhos;
    }

    public static void resolverQuebraCabeca(Qb inicial) {
        List<Qb> abertos = new ArrayList<>();
        List<Qb> fechados = new ArrayList<>();
        abertos.add(inicial);

        while (!abertos.isEmpty()) {
            Qb noAtual = abertos.stream().min((no1, no2) -> Integer.compare(no1.f, no2.f)).orElse(null);
            abertos.remove(noAtual);
            fechados.add(noAtual);
            int movimento = 0;

            if (eObjetivo(noAtual.qb_atual)) {
                System.out.println("Solucao encontrada! \n\nMovimentos realizados");
                List<int[][]> caminho = new ArrayList<>();
                while (noAtual != null) {
                    caminho.add(noAtual.qb_atual);
                    noAtual = noAtual.pai;
                }

                for (int i = caminho.size() - 1; i >= 0; i--) {
                    movimento++;
                    System.out.println("\n"+movimento +"º movimento");
                    for (int[] linha : caminho.get(i)) {
                        for (int valor : linha) {
                            System.out.print(valor + " ");
                        }
                        System.out.println();
                    }
                }
                return;
            }

            List<Qb> vizinhos = gerarVizinhos(noAtual);
            for (Qb vizinho : vizinhos) {
                boolean jaVisitado = fechados.stream().anyMatch(no -> estadosIguais(no.qb_atual, vizinho.qb_atual));
                if (!jaVisitado) {
                    abertos.add(vizinho);
                }
            }
        }
    }

    public static boolean estadosIguais(int[][] estado1, int[][] estado2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estado1[i][j] != estado2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
