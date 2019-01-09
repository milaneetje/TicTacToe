package com.example.milan.tictactoemilan;

import android.service.quicksettings.Tile;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private GameState Gstate;

    private Boolean playerOneTurn;
    private int movesPlayed;
    private Boolean gameOver;

    //  classes' constructor
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        Gstate = GameState.IN_PROGRESS;
        playerOneTurn = true;
        gameOver = false;

    }

    public TileState[][] returnboard(){
        return board;
    }

    public void setBoard(TileState[][] gameboard) {
        this.board = gameboard;
        System.out.println("Board in class game is: " + Arrays.deepToString(board));
    }

    public TileState choose(int row, int column) {

        TileState Tstate = board[row][column];
        if (!gameOver) {
            if (playerOneTurn) {
                switch (Tstate) {
                    case BLANK:
                        board[row][column] = TileState.CROSS;
                        movesPlayed += 1;
                        playerOneTurn = false;
                        break;
                    case CROSS:
                        break;
                    case CIRCLE:
                        break;
                }
            } else {
                switch (Tstate) {
                    case BLANK:
                        board[row][column] = TileState.CIRCLE;
                        movesPlayed += 1;
                        playerOneTurn = true;
                        break;
                    case CROSS:
                        break;
                    case CIRCLE:
                        break;
                }
            }
            Tstate = board[row][column];

            return Tstate;
        }
        else {
            return Tstate;
        }
    }

    public GameState won() {
        int[] scoreX = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        int[] scoreO = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (board[i][j] == TileState.CROSS) {
                    scoreX[i] += 1;
                } else if (board[i][j] == TileState.CIRCLE) {
                    scoreO[i] += 1;
                }

        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (board[j][i] == TileState.CROSS) {
                    scoreX[i + BOARD_SIZE] += 1;
                } else if (board[j][i] == TileState.CIRCLE) {
                    scoreO[i + BOARD_SIZE] += 1;
                }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] == TileState.CROSS) {
                scoreX[6] += 1;
            } else if (board[i][i] == TileState.CIRCLE) {
                scoreO[6] += 1;
            }
        }

        int j = 2;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][j] == TileState.CROSS) {
                scoreX[7] += 1;
                j = j - 1;
            } else if (board[i][j] == TileState.CIRCLE) {
                scoreO[7] += 1;
                j = j - 1;
            }
        }

        for (int m = 0; m < scoreX.length; m++) {
            if (scoreX[m] == BOARD_SIZE) {
                Gstate = GameState.PLAYER_ONE;
                gameOver = true;

            } else if (scoreO[m] == BOARD_SIZE) {
                Gstate = GameState.PLAYER_TWO;
                gameOver = true;

            } else if (movesPlayed == (BOARD_SIZE * BOARD_SIZE)) {
                Gstate = GameState.DRAW;
                gameOver = true;
            }
        }

        return Gstate;
    }
}

