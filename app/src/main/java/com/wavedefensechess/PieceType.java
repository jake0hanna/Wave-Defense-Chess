package com.wavedefensechess;

public enum PieceType
{
    KING(1000),
    QUEEN(45),
    ROOK(25),
    KNIGHT(15),
    BISHOP(15),
    PAWN(5),
    BERSERKER(3);

    private final int pointValue;

    PieceType(int value) {
        this.pointValue = value;
    }

    public int getValue() {
        return this.pointValue;
    }
}
