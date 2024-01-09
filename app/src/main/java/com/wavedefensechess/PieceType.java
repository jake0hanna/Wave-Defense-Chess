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
    BERSERKER(5, 20,null),

    Archer(15, 50, null),
    //Archer: disappears after 3 moves, has jumping, can move 1 left or right, or 2-3 forward, if it moves forward, moves back after

    Squire(10, 25, null),
    //Squire: a pawn that can move 1 in any direction, if it survives a wave, it promotes to any random unit

    Mimic(1, 100, null),
    //Mimic: can only move forward 1 square and capture 1 cell backwards, if it captures, it becomes that piece

    Magus(10, 200, null),
    //Magus: can move 1 in a horizontal line, has jumping, can move 4 squares in front of it, if it captures, destroys all pieces in a plus shape then moves back

    Ninja(50, 200, null),
    //Ninja: moves 2 diagonally, if it captures, it moves to a random place on the board

    Defender(1, 200, null),
    //Defender: can move to any cell in a 2 cell circle around the king or move 1 cell in any direction

    Warlord(75, 200, null);
    //Warlord: can move in a 3 cell circle

    //

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

    public Image getImage() { return this.image; }

}
