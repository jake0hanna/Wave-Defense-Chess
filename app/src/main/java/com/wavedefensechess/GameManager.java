package com.wavedefensechess;

public class GameManager
{
    int movesPerTurn = GameInstance.DEFAULTMOVESPERTURN;
    int maxPieces = GameInstance.MAXPIECES;

    Piece[][] board;

    Piece[] reservedPieces;

    public GameManager(int height, int width)
    {

        board = new Piece[height][width];
        reservedPieces = new Piece[maxPieces];

    }

}
