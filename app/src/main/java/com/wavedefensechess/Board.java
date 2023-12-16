package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    private Piece[][] board;
    private final int WIDTH;
    private final int HEIGHT;

    public Board(int width, int height)
    {
        board = new Piece[width][height];
        WIDTH = width;
        HEIGHT = height;

    }

    public int totalBoardValue()
    {
        return 0;
    }

    public List<Position> getPossibleMovesForPieceAt(Position position)
    {
        Piece piece = board[position.getX()][position.getY()];

        if (piece != null)
        {
            return piece.getPossibleMoves(position, this);
        }
        return new ArrayList<>();
    }

    public boolean movePiece(Position start, Position end)
    {

        return false;
    }

    public boolean onTheBoard(Position p)
    {

        int x = p.getX();
        int y = p.getY();

        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;

    }

    public boolean isOccupied(Position pos)
    {

        return false;
    }
    public boolean isFriendlyOccupied(Position pos, char c)
    {


        return false;
    }

}
