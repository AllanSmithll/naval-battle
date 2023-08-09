/*
 * Naval Battle class
 * @version 1.1.1
 * powered by @author Allan Amancio
 */
package models;

import java.util.Scanner;

public class NavalBattle {
    private static final int BOARD_SIZE = 11;
    private static final int AMOUNT_SHIPS = 5;
    private static final char WATER = '~';
    private static final char SHIP = 'N';
    private static final char FIRE = 'X';

    private Player player1;
    private Player player2;
    private int current_shift = 1;
    private char[][] board1;
    private char[][] board2;

    public NavalBattle() {
        board1 = new char[BOARD_SIZE][BOARD_SIZE];
        board2 = new char[BOARD_SIZE][BOARD_SIZE];
        inicializeBoards();
    }

    public void inicializeBoards() {
        for (int i = 1; i < BOARD_SIZE; i++) {
            for (int j = 1; j < BOARD_SIZE; j++) {
                board1[i][j] = WATER;
                board2[i][j] = WATER;
            }
        }
        positionShips();
    }

    public void positionShips() {
        int ships_placed = 0;
        while (ships_placed < AMOUNT_SHIPS) {
            int x = (int) (Math.random() * BOARD_SIZE);
            int y = (int) (Math.random() * BOARD_SIZE);
            if (board1[x][y] != 'N') {
                board1[x][y] = 'N';
            }
            x = (int) (Math.random() * BOARD_SIZE);
            y = (int) (Math.random() * BOARD_SIZE);
            if (board2[x][y] != 'N') {
                board2[x][y] = 'N';
            }
            ships_placed++;
        }
    }

    private void displayBoards() {
        System.out.print("  ");
        for (int j = 1; j < BOARD_SIZE; j++) {
            System.out.print(j + " ");
        }
        System.out.print("    ");
        for (int j = 1; j < BOARD_SIZE; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 1; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 1; j < BOARD_SIZE; j++) {
                System.out.print(board1[i][j] + " ");
            }
            System.out.print("   ");
            System.out.print(i + " ");
            for (int j = 1; j < BOARD_SIZE; j++) {
                System.out.print(board2[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isSureShot(Player jogador, int x, int y) {
        if (jogador == this.player1)
            return board2[x][y] == SHIP;
        else
            return board1[x][y] == SHIP;
    }

    public void play(String player1_p, String player2_p) throws NavalBattleException {
        Scanner sc = new Scanner(System.in);
        displayBoards();
        int missing_ships1 = AMOUNT_SHIPS;
        int missing_ships2 = AMOUNT_SHIPS;
        this.player1 = new Player(player1_p);
        this.player2 = new Player(player2_p);

        while (missing_ships1 > 0 && missing_ships2 > 0) {
            System.out.println("\nTentativa: " + current_shift);
            System.out.println("Começando por " + this.player1.getName());
            System.out.println("Digite uma linha: ");
            int x = sc.nextInt();
            System.out.println("Digite uma coluna: ");
            int y = sc.nextInt();

            if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
                throw new NavalBattleException("Coordenadas Invalidas. Tente novamente.");
            }

            if (isSureShot(player1, x, y)) {
                board2[x][y] = FIRE;
                System.out.println("Parabens! Acertou");
                missing_ships2--;
            } else {
                System.out.println("Tiro na água!");
                board2[x][y] = WATER;
            }

            System.out.println("\nTentativa: " + current_shift);
            System.out.println("Começando por " + this.player2.getName());
            System.out.println("Digite uma linha: ");
            x = sc.nextInt();
            System.out.println("Digite uma coluna: ");
            y = sc.nextInt();

            if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
                throw new NavalBattleException("Coordenadas Invalidas. Tente novamente.");
            }

            if (isSureShot(player2, x, y) == true) {
                board1[x][y] = FIRE;
                System.out.println("Parabens! Acertou");
                missing_ships1--;
            } else {
                System.out.println("Tiro na água!");
                board1[x][y] = WATER;
            }

            if (missing_ships1 == 0) {
                System.out.printf("Parabéns, %s afundou todos os navios!", player1.getName());
                break;
            } else if (missing_ships2 == 0) {
                System.out.printf("Parabéns, %s afundou todos os navios!", player2.getName());
                break;
            }
        }
        sc.close();
    }
}