package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    private final Piece[][] board;
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
        int totalValue = 0;

        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                if (board[x][y] != null)
                {
                    totalValue += board[x][y].getType().getValue();
                }
            }
        }
        return totalValue;
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



    //this needs to make sure there are no pieces between the start and end if jump is false
    public boolean movePiece(Position start, Position end, boolean jump)
    {
        if (!onTheBoard(start) || !onTheBoard(end))
        {
            return false;
        }

        Piece piece = board[start.getX()][start.getY()];

        if (piece == null || isFriendlyOccupied(end, piece.getColor()))
        {
            return false;
        }

        if (!jump)
        {
            List<Position> path = start.getPathTo(end);
            for (Position p : path)
            {
                if (isOccupied(p))
                {
                    return false;
                }
            }
        }

        board[end.getX()][end.getY()] = piece;
        board[start.getX()][start.getY()] = null;

        return true;
    }

    public void setPieceAtPosition(Position position, Piece piece)
    {
        board[position.getX()][position.getY()] = piece;
    }

    public Piece getPieceAtPosition(Position position)
    {
        return board[position.getX()][position.getY()];
    }

    public void removePieceAtPosition(Position position)
    {
        board[position.getX()][position.getY()] = null;
    }

    public PieceType getPieceTypeAtPosition(Position position)
    {
        return board[position.getX()][position.getY()].getType();
    }

    public boolean onTheBoard(Position p)
    {

        int x = p.getX();
        int y = p.getY();

        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;

    }

    public boolean isOccupied(Position pos)
    {
        int x = pos.getX();
        int y = pos.getY();
        return onTheBoard(pos) && board[x][y] != null;
    }

    public boolean isFriendlyOccupied(Position pos, char color)
    {
        int x = pos.getX();
        int y = pos.getY();
        return isOccupied(pos) && board[x][y].getColor() == color;
    }

    public boolean isPieceTypePresent(PieceType pieceType)
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                if (board[x][y] != null && board[x][y].getType() == pieceType)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isColorPresent(char color)
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                if (board[x][y] != null && board[x][y].getColor() == color)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
