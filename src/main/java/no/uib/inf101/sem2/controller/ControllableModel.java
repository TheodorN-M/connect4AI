package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;

public interface ControllableModel {
    /**
     * 
     * Returns a boolean value that represents wether the Tetromino was moved successfully
     * 
     * @param col an integer representing the column to place a piece
     */
    void placePiece(int col);



    /** */
    GridDimension getDimension();


    /**
     * The function drops the Tetromino to the spot directly under when the spacebar is pressed, if possible.
     */
    boolean dropPieces();

    /**
   * Returns the current GameState, {@code ACTIVE_GAME} or {@code GAME_OVER}
   * @return a Gamestate-Object
   */
    GameState getGameState();

    int dropTimer();

    /**
     * Moves the tetromino for each tick of the clock, if the position is legal
     * and sticks the tetromino otherwise
     */
    void clockTick();
}
