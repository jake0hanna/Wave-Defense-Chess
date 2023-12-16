package com.wavedefensechess;

public enum PieceType {
    KING(1000),
    QUEEN(45),
    ROOK(25), // You need to assign a value for ROOK
    KNIGHT(15), // Assign a value for KNIGHT
    BISHOP(15), // Assign a value for BISHOP
    PAWN(5); // Assign a value for PAWN

    private final int pointValue;

    PieceType(int value) {
        this.pointValue = value;
    }

    public int getValue() {
        return this.pointValue;
    }
}
