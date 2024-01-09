package com.wavedefensechess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity
{

    public GameManager gameManager;
    public static int BOARDWIDTH = 8;
    public static int BOARDHEIGHT = 14;
    public static int MAXPIECES = 100;
    public static int DEFAULTMOVESPERTURN = 1;

    int overlayHeight = 500;

    private TextView scoreTextView;
    private TextView pointsTextView;
    private TextView waveTextView;


    private HorizontalScrollView reservesScrollView;
    private LinearLayout reservesContainer;

    private HorizontalScrollView storeScrollView;
    private LinearLayout storeLayout;

    private Button startButton;


    private TextView highlightedCell = null;
    private int highlightedRow = -1;
    private int highlightedCol = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_instance);

        scoreTextView = findViewById(R.id.scoreTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        waveTextView = findViewById(R.id.waveTextView);

        GridLayout gameBoard = findViewById(R.id.gameBoard);

        gameManager = new GameManager(BOARDHEIGHT, BOARDWIDTH);

        int colorWhite = getResources().getColor(R.color.white);
        int colorLightPaleBlue = getResources().getColor(R.color.lightPaleBlue);

        for (int row = 0; row < BOARDHEIGHT; row++)
        {
            for (int col = 0; col < BOARDWIDTH; col++)
            {
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


    private class CellClickListener implements View.OnClickListener
    {
        private int row, col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v)
        {
            //BASED ON GAME STATE if()
            if (highlightedCell != null) {
                onCellHighlighted(row, col);
                resetHighlightedCell(); // Reset the previous cell's highlight
            }
            highlightedCell = (TextView) v;
            highlightedRow = row;
            highlightedCol = col;
            applyHighlightToCell(highlightedCell); // Apply highlight to the new cell
        }

        private void resetHighlightedCell()
        {
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



    private void onCellHighlighted(int row, int col)
    {
        // Is called when the highlight function has info and is receiving more,
        // First cell is highlightedRow, second is row

    }






    private void addPieceToReserves(Piece piece)
    {


    }


    public void updateDisplay()
    {
        scoreTextView.setText("Score: " + score);
        pointsTextView.setText("Points: " + points);
        waveTextView.setText("Wave: " + wave);

        //retreive board info from gameManager and render it

        Board board = gameManager.getBoard();

        for(int i = 0; i < BOARDHEIGHT; i++)
        {
            for(int j = 0; j < BOARDWIDTH; j++)
            {
                Piece piece = board.getPieceAtPosition(new Position(i, j));
                if(piece != null)
                {
                    //render piece

                }
            }
        }

        GameManager.GameState currentState = gameManager.getCurrentPhase();

        switch (currentState) {
            case PLACEMENT:

                reservesLayout.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);

                storeLayout.setVisibility(View.GONE);

                break;
            case WAVE:

                overlayContainer.setVisibility(View.GONE);

                break;
            case STORE:

                storeLayout.setVisibility(View.VISIBLE);
                reservesLayout.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);

                break;
        }

    }

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

// Needs to display a board, 8 wide by 14 tall
// Needs to track points, points are gained for each piece taken
// Needs to be designed with room for special types of pieces
// Initialization: Open board, allow placement of predefined pieces in a select region,
//                 allow changes until start is clicked
// game loop:
//      - accept use inputted proper move
//      - change board based on the move (overwrite piece, piece takes damage, award points)
//      - enemy piece(s) move or game detects too few pieces / too many turns
//          if the wave ends:
//              - opens store, waits for user to buy things or click done,
//              - repeat placement phase
//              - if user clicks start button start next wave
//      - repeat

// the store should appear near the top of the screen, the reserves menu slightly below it, and the start button at the same place the store does just at different times

