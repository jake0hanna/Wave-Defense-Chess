package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class Berserker extends Piece
{
    @Override
    public List<Position> getPossibleMoves(Position currentPosition, Board board)
    {
        //Just a test class so we'll make it 2f only and on death it trades
        ArrayList<Position> positions = new ArrayList<>();

        positions.add(new Position(currentPosition.getX()+2, currentPosition.getY()));

        return positions;
    }
}
