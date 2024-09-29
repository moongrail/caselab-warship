package com.caselab.warship.util;

import lombok.experimental.UtilityClass;

import java.util.InputMismatchException;
import java.util.Scanner;

@UtilityClass
public class MenuPrintUtil {
    public static int getPlayerInput(Scanner scanner) {
        int player = -1;
        while (player != 1 && player != 2) {
            System.out.println("Введите номер игрока (1 или 2): ");
            try {
                player = scanner.nextInt();
                if (player != 1 && player != 2) {
                    System.out.println("Неверный номер игрока. Введите 1 или 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число 1 или 2.");
                scanner.next();
            }
        }

        return player;
    }

    public static int[] getValidCoordinates(Scanner scanner) {
        int x = -1, y = -1;
        while (x < 0 || x >= 10 || y < 0 || y >= 10) {
            System.out.println("Введите x и y для атаки (от 0 до 9): ");
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (x < 0 || x >= 10 || y < 0 || y >= 10) {
                    System.out.println("ОТ НУЛЯ ДО ДЕВЯТИ!");
                }
            } catch (InputMismatchException e) {
                System.out.println("ЧИСЛО, ПОТОМ ПРОБЕЛ ЧИСЛО. ПРОБУЙ.");
                scanner.next();
            }
        }

        return new int[]{x, y};
    }
}
