package com.caselab.warship.service;

import com.caselab.warship.model.Player;
import com.caselab.warship.model.Point;
import com.caselab.warship.model.Ship;
import com.caselab.warship.util.BoardGameUtil;
import com.caselab.warship.util.ShipGameUtil;
import org.springframework.stereotype.Service;

import static com.caselab.warship.util.BoardGameUtil.allShipsDead;
import static com.caselab.warship.util.BoardGameUtil.printBoard;


@Service
public class GameServiceImpl implements GameService {
    private final Player player1;
    private final Player player2;

    public GameServiceImpl() {
        this.player1 = Player.builder()
                .name("Player 1")
                .build();
        this.player2 = Player.builder()
                .name("Player 2")
                .build();
    }

    @Override
    public void startGame() {
        player1.getBoard().placeShipsRandomly();
        player2.getBoard().placeShipsRandomly();
        System.out.println("Корабли успешно размещены.");
    }

    @Override
    public String playTurn(int playerNumber, int x, int y) {
        Player currentPlayer = playerNumber == 1 ? player2 : player1;
        Point target = new Point(x, y);

        Ship hitShip = BoardGameUtil.findShipAt(currentPlayer.getBoard(), target);
        if (hitShip != null) {
            ShipGameUtil.hit(hitShip, target);
            BoardGameUtil.registerHit(currentPlayer.getBoard(), target);
            if (ShipGameUtil.isDead(hitShip)) {
                return "Попадание! Корабль потоплен!";
            } else {
                return "Попадание!";
            }
        } else {
            BoardGameUtil.registerHit(currentPlayer.getBoard(), target);
            return "Промах.";
        }
    }

    @Override
    public boolean checkGameOver() {
        return allShipsDead(player1.getBoard()) || allShipsDead(player2.getBoard());
    }

    @Override
    public void printPlayerBoard(int playerNumber) {
        Player player = playerNumber == 1 ? player1 : player2;
        System.out.println("Поле игрока " + player.getName() + ":");
        printBoard(player.getBoard());
    }
}
