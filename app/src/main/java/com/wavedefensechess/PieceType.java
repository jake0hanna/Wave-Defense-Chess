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

    private final int pointValue;
    private final int cost;
    protected Image image;

    PieceType(int value, int cost, Image image) {
        this.pointValue = value;
        this.cost = cost;
        this.image = image;
    }

    public int getValue() {
        return this.pointValue;
    }
}
