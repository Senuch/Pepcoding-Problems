package org.practice.recursion_and_backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NqueensCombinations_2dAs1d_QueenChooses {
    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        // Vertical Check
        for (int i = row; i >= 0; i--) if (chess[i][col]) return false;

        // Horizontal Check
        for (int i = 0; i < chess.length; i++) if (chess[row][i]) return false;

        // Left Diagonal Check
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) if (chess[i][j]) return false;

        // Right Diagonal Check
        for (int i = row, j = col; i >= 0 && j < chess.length; i--, j++) if (chess[i][j]) return false;

        return true;
    }

    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (chess[row][col] || !IsQueenSafe(chess, row, col)) continue;

            chess[row][col] = true;
            nqueens(qpsf + 1, tq, chess, i);
            chess[row][col] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        nqueens(0, n, chess, -1);
    }
}
