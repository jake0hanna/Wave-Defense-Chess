package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    @Override
    public List<Position> getPossibleMoves(Position currentPosition, Board board) {
        List<Position> moves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // Check each direction
        for (Directions direction : Directions.values())
        {
            int newX = x;
            int newY = y;

            while (true)
            {
                newX += direction.getDeltaX();
                newY += direction.getDeltaY();
                Position newPosition = new Position(newX, newY);


                if (!board.onTheBoard(newPosition))
                {
                    break;
                }

                if (board.isOccupied(newPosition))
                {

                    if (!board.isFriendlyOccupied(newPosition, this.color))
                    {
                        moves.add(newPosition);
                    }
                    break;
                }

                moves.add(newPosition);
            }
        }

        return moves;
    }
}
