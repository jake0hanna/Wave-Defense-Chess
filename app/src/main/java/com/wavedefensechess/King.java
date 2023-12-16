package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{

    // Inside your King class
    @Override
    public List<Position> getPossibleMoves(Position currentPosition, Board board) {
        List<Position> moves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // Check each direction
        for (Directions direction : Directions.values()) {
            int newX = x + direction.getDeltaX();
            int newY = y + direction.getDeltaY();
            Position newPosition = new Position(newX, newY);

            // Check if the new position is valid
            if (board.onTheBoard(newPosition) && !board.isFriendlyOccupied(newPosition, this.color)) {
                moves.add(newPosition);
            }
        }

        return moves;
    }

}
