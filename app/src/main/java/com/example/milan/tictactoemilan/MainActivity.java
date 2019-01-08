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

public class MainActivity extends AppCompatActivity {

    Game game;
    TextView[] gameMessageIm = new TextView[3];
    String[] gameMessage = {"playerone", "playertwo", "draw"};
    TileState[][] mainboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i = 0; i < gameMessage.length; i++) {
            outState.putInt(gameMessage[i], gameMessageIm[i].getVisibility());
        }
        outState.putSerializable("mainboard", game.returnboard());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initGameMessage();
        for(int i = 0; i < gameMessage.length; i++) {
            gameMessageIm[i].setVisibility(savedInstanceState.getInt(gameMessage[i]));
        }
        mainboard = (TileState[][]) savedInstanceState.getSerializable("mainboard");
    }

    public void initGameMessage() {
        gameMessageIm[0] = findViewById(R.id.playerone);
        gameMessageIm[1] = findViewById(R.id.playertwo);
        gameMessageIm[2] = findViewById(R.id.draw);
    }

    public void tileClicked(View view) {

        initGameMessage();

        int id = view.getId();
        int row = 0;
        int column = 0;
        Button button = findViewById(R.id.button2);

        switch(id) {
            case 2131165222:
                row = 0;
                column = 0;
                button = findViewById(R.id.button2);
                break;
            case 2131165223:
                row = 0;
                column = 1;
                button = findViewById(R.id.button3);
                break;
            case 2131165224:
                row = 0;
                column = 2;
                button = findViewById(R.id.button4);
                break;
            case 2131165225:
                row = 1;
                column = 0;
                button = findViewById(R.id.button5);
                break;
            case 2131165226:
                row = 1;
                column = 1;
                button = findViewById(R.id.button6);
                break;
            case 2131165227:
                row = 1;
                column = 2;
                button = findViewById(R.id.button7);
                break;
            case 2131165228:
                row = 2;
                column = 0;
                button = findViewById(R.id.button8);
                break;
            case 2131165229:
                row = 2;
                column = 1;
                button = findViewById(R.id.button9);
                break;
            case 2131165221:
                row = 2;
                column = 2;
                button = findViewById(R.id.button10);
                break;
        }
        TileState state = game.choose(row, column);

        switch(state) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
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
