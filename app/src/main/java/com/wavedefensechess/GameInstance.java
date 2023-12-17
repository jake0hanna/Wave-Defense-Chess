package com.wavedefensechess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameInstance extends AppCompatActivity
{

    public static int BOARDWIDTH = 8;
    public static int BOARDHEIGHT = 14;
    public static int MAXPIECES = 100;
    public static int DEFAULTMOVESPERTURN = 1;

    int overlayHeight = 500;

    int points = 0;
    int score = 0;

    private TextView scoreTextView;
    private TextView pointsTextView;
    private TextView waveTextView;
    int movesPerTurn = DEFAULTMOVESPERTURN;

    Piece[][] board = new Piece[BOARDWIDTH][BOARDHEIGHT];

    Piece[] reservedPieces = new Piece[MAXPIECES];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_instance);

        scoreTextView = findViewById(R.id.scoreTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        waveTextView = findViewById(R.id.waveTextView);

        GridLayout gameBoard = findViewById(R.id.gameBoard);

        gameBoard.setColumnCount(BOARDWIDTH);
        gameBoard.setRowCount(BOARDHEIGHT);

        int colorWhite = getResources().getColor(R.color.white);
        int colorLightPaleBlue = getResources().getColor(R.color.lightPaleBlue);

        for (int row = 0; row < BOARDHEIGHT; row++) {
            for (int col = 0; col < BOARDWIDTH; col++) {
                TextView cell = new TextView(this);

                cell.setBackgroundColor((row + col) % 2 == 0 ? colorWhite : colorLightPaleBlue);

                cell.setWidth(135);
                cell.setHeight(135);

                CellClickListener clickListener = new CellClickListener(row, col);
                cell.setOnClickListener(clickListener);

                gameBoard.addView(cell);
            }
        }





    }
    private TextView highlightedCell = null;
    private int highlightedRow = -1;
    private int highlightedCol = -1;

    private class CellClickListener implements View.OnClickListener {
        private int row, col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if (highlightedCell != null) {
                resetHighlightedCell(); // Reset the previous cell's highlight
            }
            highlightedCell = (TextView) v;
            highlightedRow = row;
            highlightedCol = col;
            applyHighlightToCell(highlightedCell); // Apply highlight to the new cell
        }

        private void resetHighlightedCell() {
            // Logic to remove the highlight from the previously highlighted cell
            int originalColor = (highlightedRow + highlightedCol) % 2 == 0 ?
                    ContextCompat.getColor(getApplicationContext(), R.color.white) :
                    ContextCompat.getColor(getApplicationContext(), R.color.lightPaleBlue);
            highlightedCell.setBackgroundColor(originalColor);
        }


        private void applyHighlightToCell(TextView cell) {
            Drawable background = cell.getBackground(); // Get the cell's current background
            Drawable highlight = getResources().getDrawable(R.drawable.cell_selector); // Get the highlight drawable

            //IF CELL HAS PIECE THEN RENDER THE IMAGE AS WELL

            Drawable[] layers = {background, highlight};
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            cell.setBackground(layerDrawable);
        }
    }



    private void onCellHighlighted(int row, int col) {
        // Implement logic to use the highlighted cell information
    }






    private void addPieceToReserves(Piece piece)
    {


    }


    public void updateDisplay()
    {
        scoreTextView.setText("Score: " + score);
        pointsTextView.setText("Points: " + points);
    }

    // Example methods to update score and points
    public void increaseScore(int increment)
    {
        score += increment;
        updateDisplay();
    }

    public void increasePoints(int increment)
    {
        points += increment;
        updateDisplay();
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

