package com.example.jogodavelha;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    int actualPlayer = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //2: empty, 0: o, 1: x
    int[][] winningCombinations = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsRunning = true;

    @SuppressLint("SetTextI18n")
    public void choice(View view) {
        ImageView imageView = (ImageView) view;
        int actualState;
        actualState = Integer.parseInt(imageView.getTag().toString());
        if(gameState[actualState]==2 && gameIsRunning){
            if(actualPlayer == 1){
                imageView.setImageResource(R.drawable.tictactoe_o);
                imageView.setAlpha(0f);
                imageView.animate().alphaBy(1).setDuration(100);
                gameState[actualState] = 0;
                actualPlayer=2;
            }else{
                imageView.setImageResource(R.drawable.tictactoe_x);
                imageView.setAlpha(0f);
                imageView.animate().alphaBy(1).setDuration(100);
                gameState[actualState] = 1;
                actualPlayer=1;
            }
            String message;
            TextView txtViewWinner = (TextView) findViewById(R.id.txtViewWinner);
            Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
            for(int[] winningCombination:winningCombinations){
                 if(gameState[winningCombination[0]]==gameState[winningCombination[1]]&&gameState[winningCombination[1]]==gameState[winningCombination[2]]&&gameState[winningCombination[0]]!=2&&gameIsRunning){
                     gameIsRunning = false;
                     if(gameState[winningCombination[0]]==0){
                         message = "O ";
                     }else{
                         message = "X ";
                     }
                     txtViewWinner.setText(message + " wins");
                     txtViewWinner.setVisibility(View.VISIBLE);
                     btnPlayAgain.setVisibility(View.VISIBLE);
                     txtViewWinner.setAlpha(0f);
                     txtViewWinner.animate().alphaBy(1).setDuration(300);
                     btnPlayAgain.setAlpha(0f);
                     btnPlayAgain.animate().alphaBy(1).setDuration(1000);
                 }else if(gameState[0]!=2&&gameState[1]!=2&&gameState[2]!=2&&gameState[3]!=2&&gameState[4]!=2&&gameState[5]!=2&&gameState[6]!=2&&gameState[7]!=2&&gameState[8]!=2&&gameIsRunning){
                     gameIsRunning = false;
                     message = "Draw";
                     txtViewWinner.setText(message);
                     txtViewWinner.setVisibility(View.VISIBLE);
                     btnPlayAgain.setVisibility(View.VISIBLE);
                     txtViewWinner.setAlpha(0f);
                     txtViewWinner.animate().alphaBy(1).setDuration(300);
                     btnPlayAgain.setAlpha(0f);
                     btnPlayAgain.animate().alphaBy(1).setDuration(1000);
                 }
            }
        }
    }

    public void playAgain(View view){
        TextView txtViewWinner = (TextView) findViewById(R.id.txtViewWinner);
        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        txtViewWinner.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);

        for(int i = 0; i<9; i++) {
            gameState[i] = 2;
        }

        for(int i = 0 ; i <gridLayout.getChildCount() ; i++){
            ImageView child =(ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        actualPlayer = 1;
        gameIsRunning = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("gameState", gameState);
        outState.putInt("actualPlayer", actualPlayer);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gameState = savedInstanceState.getIntArray("gameState");
        actualPlayer = savedInstanceState.getInt("actualPlayer");

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i = 0 ; i <gridLayout.getChildCount() ; i++){
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            if(gameState[i] != 2){
                if(gameState[i] == 0)
                    child.setImageResource(R.drawable.tictactoe_o);
                else if(gameState[i] == 1)
                    child.setImageResource(R.drawable.tictactoe_x);
            }
        }
    }
}