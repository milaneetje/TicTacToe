package com.example.milan.tictactoemilan;

import android.os.PersistableBundle;
import android.service.quicksettings.Tile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import static com.example.milan.tictactoemilan.TileState.CIRCLE;
import static com.example.milan.tictactoemilan.TileState.CROSS;

public class MainActivity extends AppCompatActivity {

    Game game;
    Button[] button = new Button[9];
    String[] buttonName = {"button2", "button3", "button4", "button5", "button6", "button7",
            "button8", "button9", "button10"};
    TextView[] gameMessageIm = new TextView[3];
    String[] gameMessage = {"playerone", "playertwo", "draw"};
    TileState[][] mainboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
        initGameMessage();

        game = new Game();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i = 0; i < gameMessage.length; i++) {
            outState.putInt(gameMessage[i], gameMessageIm[i].getVisibility());
        }
        for (int j = 0; j < buttonName.length; j++) {
            outState.putCharSequence(buttonName[j], button[j].getText());
        }
//        outState.putSerializable("mainboard", game.returnboard());
//        System.out.println("Board is: " + Arrays.deepToString(game.returnboard()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for(int i = 0; i < gameMessage.length; i++) {
            gameMessageIm[i].setVisibility(savedInstanceState.getInt(gameMessage[i]));
        }
        System.out.println("Hier zijn we nu");
        for (int j =0; j < buttonName.length; j++){
            button[j].setText(savedInstanceState.getCharSequence(buttonName[j]));
        }
//        mainboard = (TileState[][]) savedInstanceState.getSerializable("mainboard");
//        System.out.println("Board is: " + Arrays.deepToString(mainboard));
//        game.setBoard(mainboard);
    }

    public void initGameMessage() {
        gameMessageIm[0] = findViewById(R.id.playerone);
        gameMessageIm[1] = findViewById(R.id.playertwo);
        gameMessageIm[2] = findViewById(R.id.draw);
    }

    public void initButton() {
        button[0] = findViewById(R.id.button2);
        button[1] = findViewById(R.id.button3);
        button[2] = findViewById(R.id.button4);
        button[3] = findViewById(R.id.button5);
        button[4] = findViewById(R.id.button6);
        button[5] = findViewById(R.id.button7);
        button[6] = findViewById(R.id.button8);
        button[7] = findViewById(R.id.button9);
        button[8] = findViewById(R.id.button10);
    }

    public void tileClicked(View view) {

        int row = 0;
        int column = 0;
        int buttonNumber = 0;

        switch(view.getId()) {
            case R.id.button2:
                row = 0;
                column = 0;
                button[0]= findViewById(R.id.button2);
                buttonNumber = 0;
                break;

            case R.id.button3:
                row = 0;
                column = 1;
                button[1] = findViewById(R.id.button3);
                buttonNumber = 1;
                break;

            case R.id.button4:
                row = 0;
                column = 2;
                button[2] = findViewById(R.id.button4);
                buttonNumber = 2;
                break;

            case R.id.button5:
                row = 1;
                column = 0;
                button[3] = findViewById(R.id.button5);
                buttonNumber = 3;
                break;

            case R.id.button6:
                row = 1;
                column = 1;
                button[4] = findViewById(R.id.button6);
                buttonNumber = 4;
                break;

            case R.id.button7:
                row = 1;
                column = 2;
                button[5] = findViewById(R.id.button7);
                buttonNumber = 5;
                break;

            case R.id.button8:
                row = 2;
                column = 0;
                button[6] = findViewById(R.id.button8);
                buttonNumber = 6;
                break;

            case R.id.button9:
                row = 2;
                column = 1;
                button[7] = findViewById(R.id.button9);
                buttonNumber = 7;
                break;

            case R.id.button10:
                row = 2;
                column = 2;
                button[8] = findViewById(R.id.button10);
                buttonNumber = 8;
                break;

        }

        TileState state = game.choose(row, column);

        switch(state) {
            case CROSS:
                button[buttonNumber].setText("X");
                break;
            case CIRCLE:
                button[buttonNumber].setText("O");
                break;
        }

        GameState won = game.won();

        switch(won) {
            case PLAYER_ONE:
                gameMessageIm[0].setVisibility(View.VISIBLE);
                break;
            case PLAYER_TWO:
                gameMessageIm[1].setVisibility(View.VISIBLE);
                break;
            case DRAW:
                gameMessageIm[2].setVisibility(View.VISIBLE);
                break;
        }
    }

    public void resetClicked(View view) {
        Button button = findViewById(R.id.button2);
        button.setText(" ");
        button = findViewById(R.id.button3);
        button.setText(" ");
        button = findViewById(R.id.button4);
        button.setText(" ");
        button = findViewById(R.id.button5);
        button.setText(" ");
        button = findViewById(R.id.button6);
        button.setText(" ");
        button = findViewById(R.id.button7);
        button.setText(" ");
        button = findViewById(R.id.button8);
        button.setText(" ");
        button = findViewById(R.id.button9);
        button.setText(" ");
        button = findViewById(R.id.button10);
        button.setText(" ");

        gameMessageIm[0].setVisibility(View.INVISIBLE);
        gameMessageIm[1].setVisibility(View.INVISIBLE);
        gameMessageIm[2].setVisibility(View.INVISIBLE);
        game = new Game();
    }
}
