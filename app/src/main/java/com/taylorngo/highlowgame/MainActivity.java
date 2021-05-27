package com.taylorngo.highlowgame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int numberToGuess;
    private int guessLimit;
    private int totalTries;

    private TextView resultText;
    private TextView guessLimitText;
    private TextView totalGuessesText;
    private EditText guessEditText;
    private Button guessButton;
    private Button playAgainButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        resultText.setVisibility(View.INVISIBLE);

        guessLimit = 10;
        totalTries = 0;

        guessLimitText = findViewById(R.id.guessLimitText);
        guessLimitText.setText(guessLimitText.getText() + "" + guessLimit);

        totalGuessesText = findViewById(R.id.numTriesText);
        totalGuessesText.setText(totalGuessesText.getText() + "" + totalTries);

        guessEditText = findViewById(R.id.guessEditText);

        guessButton = findViewById(R.id.guessBtn);
        playAgainButton = findViewById(R.id.playAgainBtn);
        playAgainButton.setEnabled(false);

        numberToGuess = (int)(Math.random() * (100)) + 1;
    }

    @SuppressLint("SetTextI18n")
    public void makeGuess(View view){
//        System.out.println(numberToGuess);
        resultText.setVisibility(View.VISIBLE);
        String numberGuessedString = guessEditText.getText().toString();
        if(numberGuessedString.isEmpty()){
            resultText.setText("Enter a valid guess.");
            resultText.setTextColor(Color.parseColor("#e3dc0b"));
            return;
        }
        int numberGuessed = Integer.parseInt(numberGuessedString);
        if(numberGuessed < numberToGuess){
            resultText.setText("Your guess is too low.");
            resultText.setTextColor(Color.RED);
        }
        else if(numberGuessed > numberToGuess){
            resultText.setText("Your guess is too high.");
            resultText.setTextColor(Color.RED);
        }
        else{
            winGame();
//            resultText.setText("Your guess is correct!");
//            resultText.setTextColor(Color.parseColor("#00d928"));
        }
        totalTries++;
        guessLimit--;
        updateGuessCount();
        if(guessLimit == 0){
            loseGame();
        }
        guessEditText.getText().clear();
    }

    @SuppressLint("SetTextI18n")
    public void updateGuessCount(){
        totalGuessesText.setText("Number of Guesses: " + totalTries);
        guessLimitText.setText("Remaining Guesses: " + guessLimit);
    }

    @SuppressLint("SetTextI18n")
    public void loseGame(){
        resultText.setText("YOU LOSE. The number was: " + numberToGuess);
        resultText.setTextColor(Color.RED);
        guessButton.setEnabled(false);
        guessEditText.setEnabled(false);
        playAgainButton.setEnabled(true);
    }

    @SuppressLint("SetTextI18n")
    public void winGame(){
        resultText.setText("YOU WIN!!");
        resultText.setTextColor(Color.parseColor("#00d928"));
        guessButton.setEnabled(false);
        guessEditText.setEnabled(false);
        playAgainButton.setEnabled(true);
    }

    public void playAgain(View view){
        resultText.setVisibility(View.INVISIBLE);
        guessLimit = 10;
        totalTries = 0;
        updateGuessCount();
        guessButton.setEnabled(true);
        numberToGuess = (int)(Math.random() * (100)) + 1;
        playAgainButton.setEnabled(false);
        guessEditText.setEnabled(true);
        guessEditText.getText().clear();
    }

}
