package com.caselab.warship.util;

import com.caselab.warship.model.Board;
import com.caselab.warship.model.Point;
import com.caselab.warship.model.Ship;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

import static com.caselab.warship.util.BoardGameUtil.addShip;

@UtilityClass
public class ShipPlacerUtil {
    private final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    public static void placeAllShips(Board board) {
        Random random = new Random();
        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                boolean horizontal = random.nextBoolean();
                Ship ship = Ship.builder()
                        .size(size)
                        .build();
                ship.initializeHitStatus();
                if (canPlaceShip(board, ship, x, y, horizontal)) {
                    placeShip(board, ship, x, y, horizontal);
                    placed = true;
                }
            }
        }
    }

    private static boolean canPlaceShip(Board board, Ship ship, int x, int y, boolean horizontal) {
        int shipSize = ship.getSize();

        if (horizontal) {
            if (x + shipSize > 10) {
                return false;
            }
        } else {
            if (y + shipSize > 10) {
                return false;
            }
        }

        for (int i = 0; i < shipSize; i++) {
            int checkX = horizontal ? x + i : x;
            int checkY = horizontal ? y : y + i;

            if (!isCellAvailable(board, checkX, checkY)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isCellAvailable(Board board, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int checkX = x + i;
                int checkY = y + j;

                if (checkX >= 0 && checkX < 10 && checkY >= 0 && checkY < 10) {
                    if (board.getCell(checkX, checkY) == 'S') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private  static void placeShip(Board board, Ship ship, int x, int y, boolean horizontal) {
        List<Point> coordinates = ship.getCoordinates();
        coordinates.clear();

        for (int i = 0; i < ship.getSize(); i++) {
            int checkX = horizontal ? x + i : x;
            int checkY = horizontal ? y : y + i;

            if (checkX >= 10 || checkY >= 10) {
                throw new IllegalArgumentException("Корабль выходит за пределы поля!");
            }

            coordinates.add(new Point(checkX, checkY));
        }

        addShip(board, ship);
    }
}
