package com.wavedefensechess;

import java.util.List;

public abstract class Piece
{

    protected PieceType type;
    protected char color;
    protected int timesMoved;

    public abstract List<Position> getPossibleMoves(Position currentPosition, Board board);


    public PieceType getType()
    {
        return type;
    }

    public char getColor()
    {
        return color;
    }

    public int getTimesMoved()
    {
        return timesMoved;
    }

    public void setTimesMoved(int timesMoved)
    {
        this.timesMoved = timesMoved;
    }
}
