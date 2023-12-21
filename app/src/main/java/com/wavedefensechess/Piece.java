package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class Piece
{
    protected PieceType type;
    protected char color;
    protected int timesMoved;
    protected List<Effect> effects;

    public Piece(PieceType type, char color)
    {
        this.type = type;
        this.color = color;
        this.timesMoved = 0;
        effects = new ArrayList<>();
    }
    public Piece(PieceType type, char color, List<Effect> effects)
    {
        this.type = type;
        this.color = color;
        this.timesMoved = 0;
        this.effects = effects;
    }

    public List<Position> getPossibleMoves(Position currentPosition, Board board)
    {
        PieceType type = board.getPieceTypeAtPosition(currentPosition);

        switch(type)
        {
            case KING:
                return null;
            case QUEEN:
                return null;
            case ROOK:
                return null;
            case KNIGHT:
                return null;
            case BISHOP:
                return null;
            case PAWN:
                return null;
            case BERSERKER:
                return null;
            default:
                return null;

        }
    }

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


}
