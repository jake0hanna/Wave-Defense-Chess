package com.wavedefensechess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameManager
{
    int currentPoints;
    int score;

    Board board;

    Map<PieceType, Integer> reservedPieces;


    public GameManager(int height, int width)
    {
        board = new Board(height, width);
        reservedPieces = new HashMap<>();

        for(PieceType pieceType : PieceType.values())
        {
            reservedPieces.put(pieceType, 0);
        }
    }

    public boolean placePieceFromReservesOnBoard(PieceType pieceType, Position position)
    {
        if (reservedPieces.get(pieceType) > 0) {
            Piece piece = new Piece(pieceType, 'w', null);

            if (board.getPieceAtPosition(position) == null)
            {
                board.setPieceAtPosition(position, piece);
            }
            else {
                addPieceToReserves(board.getPieceTypeAtPosition(position));
                board.setPieceAtPosition(position, piece);
            }
            reservedPieces.remove(piece);
            return true;
        }
        return false;
    }

    //needs logic for points
    public boolean attemptToMovePiece(Position start, Position end)
    {
        if (!board.onTheBoard(start) || !board.onTheBoard(end))
        {
            return false;
        }

        Piece piece = board.getPieceAtPosition(start);

        if (piece == null || board.isFriendlyOccupied(end, piece.getColor()))
        {
            return false;
        }

        board.movePiece(start, end);

        return true;
    }

    public void removePieceAtPosition(Position position)
    {
        board.setPieceAtPosition(position, null);
    }

    public void addPieceToReserves(PieceType pieceType)
    {
        reservedPieces.put(pieceType, reservedPieces.get(pieceType) + 1);
    }

    public void removePieceFromReserves(PieceType pieceType)
    {
        reservedPieces.put(pieceType, reservedPieces.get(pieceType) - 1);
    }



}
