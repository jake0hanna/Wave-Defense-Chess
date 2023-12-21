package com.wavedefensechess;

import android.media.Image;

public enum PieceType
{
    KING(1000, null),
    QUEEN(45, null),
    ROOK(25, null),
    KNIGHT(15, null),
    BISHOP(15, null),
    PAWN(5, null),
    BERSERKER(3, null);

    private final int pointValue;

    protected Image image;

    PieceType(int value, Image image) {
        this.pointValue = value;
        this.image = image;
    }

    public int getValue() {
        return this.pointValue;
    }
}
