package com.caselab.warship.service;

public interface GameService {
    void startGame();
    String playTurn(int playerNumber, int x, int y);
    boolean checkGameOver();
    void printPlayerBoard(int playerNumber);
}
