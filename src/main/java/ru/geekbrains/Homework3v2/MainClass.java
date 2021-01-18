package ru.geekbrains.Homework3v2;

import java.util.Random;
import java.util.Scanner;


public class MainClass {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        while (true) {
            initMap();
            printMap();
            while (true) {
                humanTurn();
                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил человек");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)) {
                    System.out.println("Победил Искусственный Интеллект");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
            }
            System.out.println("Хотите сыграть еще? 1 - да");
            if (!sc.next().equals("1")) break;
        }
    }

    public static boolean checkWin(char sym) {
        for (int i = 0; i <= DOTS_TO_WIN; i++) {
            for (int j = 0; j < 2; j++) {
                if (map[i][j] == sym && map[i][j + 1] == sym && map[i][j + 2] == sym &&
                        map[i][j + 3] == sym) return true;
                if (map[j][i] == sym && map[j + 1][i] == sym && map[j + 2][i] == sym &&
                        map[j + 3][i] == sym) return true;
            }
        }
        for (int j = 0, i = 1; j < 2; j++, i--) { // цикл для диагоналей
            if (map[j][j] == sym && map[j + 1][j + 1] == sym && map[j + 2][j + 2] == sym &&
                    map[j + 3][j + 3] == sym) return true;
            if (map[i][j] == sym && map[i + 1][j + 1] == sym && map[i + 2][j + 2] == sym &&
                    map[i + 3][j + 3] == sym) return true;
            if (map[i + 3][j] == sym && map[i + 2][j + 1] == sym && map[i + 1][j + 2] == sym &&
                    map[i][j + 3] == sym) return true;
            if (map[j + 3][j] == sym && map[j + 2][j + 1] == sym && map[j + 1][j + 2] == sym &&
                    map[j][j + 3] == sym) return true;
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x = 0, y = 0;
        boolean found = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < 2; i++) {
                if (map[i][j] == map[i + 1][j] && map[i][j] != DOT_EMPTY && map[i + 2][j] == DOT_EMPTY) {
                    y = i + 2;
                    x = j;
                    found = true;
                    break;
                }
                if (map[i + 3][j] == map[i + 2][j] && map[i + 3][j] != DOT_EMPTY && map[i + 1][j] == DOT_EMPTY) {
                    y = i + 1;
                    x = j;
                    found = true;
                    break;
                }
                if (map[j][i] == map[j][i + 1] && map[j][i] != DOT_EMPTY && map[j][i + 2] == DOT_EMPTY) {
                    y = j;
                    x = i + 2;
                    found = true;
                    break;
                }
                if (map[j][i + 3] == map[j][i + 2] && map[j][i + 3] != DOT_EMPTY && map[j][i + 1] == DOT_EMPTY) {
                    y = j;
                    x = i + 1;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (!found)
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));

        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

