package com.wavedefensechess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager
{
    public enum GameState
    {
        PLACEMENT,
        WAVE,
        STORE
    }

    GameState currentPhase;
    int currentPoints;
    int score;

    int turnCount;
    int currentWave;

    Board board;

    Map<PieceType, Integer> reservedPieces;


    /*


        Game Starts, get default pieces to be placed, allow player to place pieces on the bottom 3 rows of the board
            MUST REQUIRE KING TO BE PRESENT BEFORE THE START BUTTON WORKS
        wave is placed on the board, waits for input of first move from player,
        if players move is valid, move is made, if not, continue waiting for a valid move
        enemy moves x times,
        repeat until there the player loses their king or there are no enemies on the board
        if the player loses their king, game over
        if there are no enemies on the board, the wave is over, points/score are awarded,
            move all player pieces from the board to the reserves and the store is opened
        repeat from the start of the placement phase and continue until the player loses, record score and return to main menu



     */

    public GameManager(int height, int width)
    {
        board = new Board(width, height);
        reservedPieces = new HashMap<>();

        for(PieceType pieceType : PieceType.values())
        {
            reservedPieces.put(pieceType, 0);
        }

        reservedPieces.put(PieceType.KING, 1);

        turnCount = 0;
        score = 0;
        currentPoints = 0;
        currentWave = 1;

        currentPhase = GameState.PLACEMENT;

    }

    public boolean startWave()
    {
        if(currentPhase != GameState.PLACEMENT)
        {
            return false;
        }
        if(reservedPieces.get(PieceType.KING) == 0)
        {
            return false;
        }
        else
        {
            currentPhase = GameState.WAVE;
            generateWave(currentWave);
            return true;
        }
    }

    public boolean acceptInput(Position start, Position end)
    {
        if(currentPhase == GameState.WAVE)
        {
            return attemptToMovePiece(start, end);
        }

        return false;
    }

    public boolean placePiece(Position position, PieceType pieceType)
    {
        if(currentPhase == GameState.PLACEMENT)
        {
            return placePieceFromReserves(pieceType, position);
        }
        return false;
    }

    public boolean checkIfGameIsOver()
    {
        //should return true if the game is over, false if it is not, should check the board state for a king after every move
        return !board.isPieceTypePresent(PieceType.KING);

    }

    public boolean checkIfWaveIsOver()
    {
        //should return true if the wave is over, false if it is not, should check the board state for enemies after every move
        return !board.isColorPresent('b');
    }

    public List<Position> getMovesForPieceAt(Position position)
    {
        return board.getPossibleMovesForPieceAt(position);
    }

    public boolean placePieceFromReserves(PieceType pieceType, Position position)
    {
        if (reservedPieces.get(pieceType) > 0)
        {
            Piece piece = new Piece(pieceType, 'w');

            if (board.getPieceAtPosition(position) == null)
            {
                board.setPieceAtPosition(position, piece);
            }
            else
            {
                addPieceToReserves(board.getPieceTypeAtPosition(position));
                board.setPieceAtPosition(position, piece);
            }
            removePieceFromReserves(piece.getType());
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

        board.movePiece(start, end, board.getPieceAtPosition(start).isJump());

        return true;
    }

    public void removePieceAtPosition(Position position)
    {
        board.setPieceAtPosition(position, null);
    }

    public Map<PieceType, Integer> getReservedPieces()
    {
        return reservedPieces;
    }

    public void addPieceToReserves(PieceType pieceType)
    {
        reservedPieces.put(pieceType, reservedPieces.get(pieceType) + 1);
    }

    public void removePieceFromReserves(PieceType pieceType)
    {
        reservedPieces.put(pieceType, reservedPieces.get(pieceType) - 1);
    }

    public void addPoints(int points)
    {
        score += points;
        currentPoints += points;
    }

    public void removePoints(int points)
    {
        currentPoints -= points;
    }

    public void generateWave(int i)
    {
        //I need some format to store predefined waves and then after it runs out of predefined waves it needs to randomly generate
        //waves based on the current wave number



    }

    public GameState getCurrentPhase()
    {
        return currentPhase;
    }


    public void setCurrentPhase(GameState currentPhase) {
        this.currentPhase = currentPhase;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setReservedPieces(Map<PieceType, Integer> reservedPieces) {
        this.reservedPieces = reservedPieces;
    }
}
