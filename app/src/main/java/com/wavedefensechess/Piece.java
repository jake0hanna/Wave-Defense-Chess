package com.wavedefensechess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Piece
{
    protected PieceType type;
    protected char color;
    protected int timesMoved;
    protected List<Effect> effects;

    protected boolean jump = false;

    public Piece(PieceType type, char color, List<Effect> effects, boolean jump)
    {
        this.type = type;
        this.color = color;
        this.timesMoved = 0;
        this.effects = effects == null ? new ArrayList<>() : new ArrayList<>(effects);

        if(type == PieceType.KNIGHT)
            this.jump = true;
        else
            this.jump = jump;

    }

    public Piece(PieceType type, char color)
    {
        this(type, color, new ArrayList<>(), false);
    }

    public List<Position> getPossibleMoves(Position currentPosition, Board board)
    {
        PieceType type = board.getPieceTypeAtPosition(currentPosition);

        ArrayList<Position> possibleMoves = new ArrayList<>();

        switch(type)
        {
            case KING:
                for(Directions direction : Directions.values())
                {
                    Position position = new Position(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());

                    if(board.onTheBoard(position))
                    {
                        if(board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                        else if(board.getPieceAtPosition(position).getColor() != color)
                        {
                            possibleMoves.add(position);
                        }

                    }
                }
                return possibleMoves;
            case QUEEN:
                for(Directions direction : Directions.values())
                {
                    Position position = new Position(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());

                    while(board.onTheBoard(position))
                    {
                        if(board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                        else if(board.getPieceAtPosition(position).getColor() != color)
                        {
                            possibleMoves.add(position);
                            break;
                        }
                        else
                        {
                            break;
                        }
                        position = new Position(position.getX() + direction.getX(), position.getY() + direction.getY());
                    }
                }
                return possibleMoves;
            case ROOK:
                for(Directions direction : Directions.values())
                {
                    if(direction == Directions.UP_LEFT || direction == Directions.UP_RIGHT || direction == Directions.DOWN_LEFT || direction == Directions.DOWN_RIGHT)
                    {
                        continue;
                    }

                    Position position = new Position(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());

                    while(board.onTheBoard(position))
                    {
                        if(board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                        else if(board.getPieceAtPosition(position).getColor() != color)
                        {
                            possibleMoves.add(position);
                            break;
                        }
                        else
                        {
                            break;
                        }
                        position = new Position(position.getX() + direction.getX(), position.getY() + direction.getY());
                    }
                }
                return possibleMoves;
            case KNIGHT:
                //NEEDS TO FIND SQUARES THAT KNIGHTS MOVE IN
                possibleMoves.add(new Position(currentPosition.getX() + 2, currentPosition.getY() + 1));
            case BISHOP:
                for(Directions direction : Directions.values())
                {
                    if(direction == Directions.UP || direction == Directions.DOWN || direction == Directions.LEFT || direction == Directions.RIGHT)
                    {
                        continue;
                    }

                    Position position = new Position(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());

                    while(board.onTheBoard(position))
                    {
                        if(board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                        else if(board.getPieceAtPosition(position).getColor() != color)
                        {
                            possibleMoves.add(position);
                            break;
                        }
                        else
                        {
                            break;
                        }
                        position = new Position(position.getX() + direction.getX(), position.getY() + direction.getY());
                    }
                }
                return possibleMoves;
            case PAWN:
                if(color == 'w')
                {
                    Position position = new Position(currentPosition.getX() - 1, currentPosition.getY());

                    if(board.onTheBoard(position) && board.getPieceAtPosition(position) == null)
                    {
                        possibleMoves.add(position);
                    }

                    if(timesMoved == 0)
                    {
                        position = new Position(currentPosition.getX() - 2, currentPosition.getY());

                        if(board.onTheBoard(position) && board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                    }

                    position = new Position(currentPosition.getX() - 1, currentPosition.getY() - 1);

                    if(board.onTheBoard(position) && board.getPieceAtPosition(position) != null && board.getPieceAtPosition(position).getColor() != color)
                    {
                        possibleMoves.add(position);
                    }

                    position = new Position(currentPosition.getX() - 1, currentPosition.getY() + 1);

                    if(board.onTheBoard(position) && board.getPieceAtPosition(position) != null && board.getPieceAtPosition(position).getColor() != color)
                    {
                        possibleMoves.add(position);
                    }
                }
                else
                {
                    Position position = new Position(currentPosition.getX() + 1, currentPosition.getY());

                    if(board.onTheBoard(position) && board.getPieceAtPosition(position) == null)
                    {
                        possibleMoves.add(position);
                    }

                    if(timesMoved == 0)
                    {
                        position = new Position(currentPosition.getX() + 2, currentPosition.getY());

                        if(board.onTheBoard(position) && board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                    }

                    position = new Position(currentPosition.getX() + 1, currentPosition.getY() - 1);

                    if(board.onTheBoard(position) && board.getPieceAtPosition(position) != null && board.getPieceAtPosition(position).getColor() != color)
                    {
                        possibleMoves.add(position);
                    }

                    position = new Position(currentPosition.getX() + 1, currentPosition.getY() + 1);

                    if(board.onTheBoard(position) && board.getPieceAtPosition(position) != null && board.getPieceAtPosition(position).getColor() != color)
                    {
                        possibleMoves.add(position);
                    }
                }
                return possibleMoves;
            case BERSERKER://this piece is intended to move two cells in any direction, but it can only move in a straight line(only up, left, right, down), is a jumping piece
                for(Directions direction : Directions.values())
                {
                    if(direction == Directions.UP_LEFT || direction == Directions.UP_RIGHT || direction == Directions.DOWN_LEFT || direction == Directions.DOWN_RIGHT)
                    {
                        continue;
                    }

                    Position position = new Position(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());

                    while(board.onTheBoard(position))
                    {
                        if(board.getPieceAtPosition(position) == null)
                        {
                            possibleMoves.add(position);
                        }
                        else if(board.getPieceAtPosition(position).getColor() != color)
                        {
                            possibleMoves.add(position);
                            break;
                        }
                        else
                        {
                            break;
                        }
                        position = new Position(position.getX() + direction.getX(), position.getY() + direction.getY());
                    }
                }
                return possibleMoves;
            case ARCHER://this piece is intended to move one cell left or right, or 2-3 cells forward, if it moves forward, it moves back after, is a jumping piece
                for(Directions direction : Directions.values())
                {
                    if(direction ==  Directions.DOWN || direction == Directions.UP_LEFT || direction == Directions.DOWN_LEFT || direction == Directions.UP_RIGHT || direction == Directions.DOWN_RIGHT)
                    {
                        continue;
                    }

                    Position position = new Position(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());

                    if(direction == Directions.UP)
                    {//add two and then three spaces forward and add that to possible moves
                        if(board.onTheBoard(position))
                        {
                            if(board.getPieceAtPosition(position) == null)
                            {
                                possibleMoves.add(position);
                            }
                            else if(board.getPieceAtPosition(position).getColor() != color)
                            {
                                possibleMoves.add(position);
                            }
                        }
                    }
                    else
                    {//move one left or right
                        if(board.onTheBoard(position))
                        {
                            if(board.getPieceAtPosition(position) == null)
                            {
                                possibleMoves.add(position);
                            }
                            else if(board.getPieceAtPosition(position).getColor() != color)
                            {
                                possibleMoves.add(position);
                            }
                        }
                    }
                }
                return possibleMoves;
            case SQUIRE://this piece is intended to move one cell in any direction, if it survives a wave, it promotes to any random unit
                return possibleMoves;
            case MIMIC://this piece is intended to move forward one cell and capture one cell backwards, if it captures, it becomes that piece
                return possibleMoves;
            case MAGUS://this piece is intended to move one cell in a horizontal line, has jumping, can move 4 squares in front of it, if it captures, destroys all pieces in a plus shape then moves back
                return possibleMoves;
            case NINJA://this piece is intended to move two cells diagonally, if it captures, it moves to a random place on the board
                return possibleMoves;
            case DEFENDER://this piece is intended to move to any cell in a 2 cell circle around the king or move 1 cell in any direction
                return possibleMoves;
            case WARLORD://this piece is intended to move in a 3 cell circle
                return possibleMoves;

            default:
                return null;

        }
    }

    public PieceType getType()
    {
        return type;
    }

    public char getColor()
    {
        return color;
    }

    public int getTimesMoved()
    {
        return timesMoved;
    }

    public void setTimesMoved(int timesMoved)
    {
        this.timesMoved = timesMoved;
    }

    public void incrementMoves()
    {
        this.timesMoved++;
    }

    public void addEffect(Effect effect)
    {
        effects.add(effect);
    }

    public List<Effect> getEffects()
    {
        return effects;
    }

    public boolean isJump()
    {
        return jump;
    }

}
