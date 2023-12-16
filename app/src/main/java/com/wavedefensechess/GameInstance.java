package com.wavedefensechess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameInstance extends AppCompatActivity
{

    public static int BOARDWIDTH = 8;
    public static int BOARDHEIGHT = 14;
    public static int MAXPIECES = 100;

    public static int DEFAULTMOVESPERTURN = 1;

    int points = 0;
    int score = 0;
    int movesPerTurn = DEFAULTMOVESPERTURN;

    Piece[][] board = new Piece[BOARDWIDTH][BOARDHEIGHT];

    Piece[] ownedPieces = new Piece[MAXPIECES];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_instance);
    }




}

// Needs to display a board, probably 8 wide by 14 tall
// Needs to track points, points are gained for each piece taken, maybe some combo mechanics
// Needs to be designed with room for special types of pieces
// Initialization: Open board, allow placement of predefined pieces in a select region,
//                 allow changes until start is clicked
// game loop:
//      - accept use inputted proper move
//      - change board based on the move (overwrite piece, piece takes damage, award points)
//      - enemy piece(s) move or game detects too few pieces / too many turns
//          if the wave ends:
//              - opens store, waits for user to buy things or click done,
//              - if the next wave allows piece placement repeat initialization
//              - else start next wave
//      - repeat

