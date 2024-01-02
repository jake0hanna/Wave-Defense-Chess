package com.wavedefensechess;

import android.media.Image;

public enum PieceType
{
    KING(1000, 1000000000, null),
    QUEEN(50, 200, null),
    ROOK(25, 100, null),
    KNIGHT(15, 50, null),
    BISHOP(15, 50, null),
    PAWN(5, 10, null),
    BERSERKER(5, 20,null);

    //Archer: disappears after 3 moves, has jumping, can move 1 left or right, or 2-3 forward, if it moves forward, moves back after

    //Squire: a pawn that can move 1 in any direction, if it survives a wave, it promotes randomly to any unit

    //Mimic: can only move forward 1-2 squares, if it captures, it becomes that piece

    //Magus: can move 1 in a line, has jumping, can move 4 squares in front of it, if it captures, destroys all pieces in a plus shape then moves back

    //Ninja: moves 2 diagonally, if it captures, it randomly moves

    private final int pointValue;
    private final int cost;
    protected Image image;

    PieceType(int value, int cost, Image image)
    {
        this.pointValue = value;
        this.cost = cost;
        this.image = image;
    }

    public int getValue() {
        return this.pointValue;
    }

    public int getCost() { return this.cost; }

}
