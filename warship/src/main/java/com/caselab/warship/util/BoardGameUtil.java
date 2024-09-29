package com.caselab.warship.util;

import com.caselab.warship.model.Board;
import com.caselab.warship.model.Point;
import com.caselab.warship.model.Ship;
import lombok.experimental.UtilityClass;


import static com.caselab.warship.util.ShipGameUtil.hit;
import static com.caselab.warship.util.ShipGameUtil.isDead;

@UtilityClass
public class BoardGameUtil {
    public static boolean registerHit(Board board, Point point) {
        int x = point.getX();
        int y = point.getY();
        char[][] grid = board.getGrid();

        if (grid[x][y] == 'S') {
            Ship hitShip = findShipAt(board, point);
            if (hitShip != null) {
                grid[x][y] = 'X';
                hit(hitShip, point);
                return true;
            }
        } else if (grid[x][y] == '~') {
            grid[x][y] = 'O';
            return false;
        }

        return false;
    }

    public static void addShip(Board board, Ship ship) {
        board.getShips().add(ship);
        for (Point point : ship.getCoordinates()) {
            board.getGrid()[point.getX()][point.getY()] = 'S';
        }
    }

    public static void printBoard(Board board) {
        char[][] grid = board.getGrid();
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    public static boolean allShipsDead(Board board) {
        for (Ship ship : board.getShips()) {
            if (!isDead(ship)) return false;
        }

        return true;
    }

    public static Ship findShipAt(Board board, Point point) {
        for (Ship ship : board.getShips()) {
            if (ship.getCoordinates().contains(point)) {
                return ship;
            }
        }

        return null;
    }

}
