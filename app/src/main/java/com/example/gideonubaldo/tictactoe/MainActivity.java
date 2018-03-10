package com.example.gideonubaldo.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 is x and 1 is circle
    int activePlayer = 0;

    boolean gameIsActive = true;
    int[] gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}
                                };


    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive){

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            }
            else{
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
        }

        // check for a winner
        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[2]] != 2 &&
               gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
               gameState[winningPosition[1]] == gameState[winningPosition[2]]){
                //someone has won!
                String winner = "Player 1";
                gameIsActive = false;

                if(gameState[winningPosition[0]] == 1){
                    winner = "Player 2";
                }

                TextView winMessage = (TextView) findViewById(R.id.winnerMessage);
                winMessage.setText(winner + " won");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }else{
                boolean gameIsOver = true;
                for(int counterState: gameState){
                    if(counterState == 2) {
                        gameIsOver = false;
                    }
                }
                if(gameIsOver){
                    TextView winMessage = (TextView) findViewById(R.id.winnerMessage);
                    winMessage.setText("It's a draw");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int x = 0; x < gameState.length; x++){
            gameState[x] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int x = 0; x < gridLayout.getChildCount(); x++){
            ((ImageView) gridLayout.getChildAt(x)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
