package com.wavedefensechess;

import java.util.List;

public abstract class Piece
{

    protected PieceType type;
    protected char color;
    protected int timesMoved;
    protected List<Effect> effects;
    protected int image;

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

    public void incrementMoves()
    {
        this.timesMoved++;
    }

    public void addEffect(Effect effect)
    {
        effects.add(effect);
    }

    public List<Effect> getEffects()
    {
        return effects;
    }
    public int getImageResource() {
        return image;
    }

}
