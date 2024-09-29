package com.caselab.warship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.caselab.warship.util.ShipPlacerUtil.placeAllShips;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Board {
    private final int SIZE = 10;
    private char[][] grid;
    private List<Ship> ships;

    public Board() {
        grid = new char[SIZE][SIZE];
        ships = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(grid[i], '~');
        }
    }

    public void placeShipsRandomly() {
        placeAllShips(this);
    }

    public char getCell(int x, int y) {
        return grid[x][y];
    }
}
