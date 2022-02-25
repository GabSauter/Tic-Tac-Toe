package com.example.jogodavelha;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int actualPlayer = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //2: empty, 0: o, 1: x
    int[][] winningCombinations = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsRunning = true;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPlayAgain) {
            TextView txtViewWinner = findViewById(R.id.txtViewWinner);
            Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
            GridLayout gridLayout = findViewById(R.id.gridLayout);
            txtViewWinner.setVisibility(View.INVISIBLE);
            btnPlayAgain.setVisibility(View.INVISIBLE);

            for (int i = 0; i < 9; i++) {
                gameState[i] = 2;
            }

            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView child = (ImageView) gridLayout.getChildAt(i);
                child.setImageDrawable(null);
            }
            actualPlayer = 1;
            gameIsRunning = true;
        }else{
            Log.d("MainActivity", "click!");
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
                TextView txtViewWinner = findViewById(R.id.txtViewWinner);
                Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
                for(int[] winningCombination:winningCombinations){
                    if(gameState[winningCombination[0]]==gameState[winningCombination[1]]&&gameState[winningCombination[1]]==gameState[winningCombination[2]]&&gameState[winningCombination[0]]!=2&&gameIsRunning){
                        gameIsRunning = false;
                        if(gameState[winningCombination[0]]==0){
                            message = "O ";
                        }else{
                            message = "X ";
                        }
                        txtViewWinner.setText(getString(R.string.wins, message));
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView one = findViewById(R.id.imageView);
        one.setOnClickListener(this); // calling onClick() method
        ImageView two = findViewById(R.id.imageView2);
        two.setOnClickListener(this);
        ImageView three = findViewById(R.id.imageView3);
        three.setOnClickListener(this);
        ImageView four = findViewById(R.id.imageView4);
        four.setOnClickListener(this);
        ImageView five = findViewById(R.id.imageView5);
        five.setOnClickListener(this);
        ImageView six = findViewById(R.id.imageView6);
        six.setOnClickListener(this);
        ImageView seven = findViewById(R.id.imageView7);
        seven.setOnClickListener(this);
        ImageView eight = findViewById(R.id.imageView8);
        eight.setOnClickListener(this);
        ImageView nine = findViewById(R.id.imageView9);
        nine.setOnClickListener(this);

        Button playAgainButton = findViewById(R.id.btnPlayAgain);
        playAgainButton.setOnClickListener(this);
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