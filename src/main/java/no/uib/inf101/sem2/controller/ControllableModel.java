package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.ai.Ai;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;

public interface ControllableModel {
    /**
     * 
     * Returns a boolean value that represents wether the Tetromino was moved
     * successfully
     * 
     * @param col an integer representing the column to place a piece
     */
    void placePiece(int col);

    /** */
    GridDimension getDimension();

    /**
     * Returns the current GameState, {@code ACTIVE_GAME} or {@code GAME_OVER}
     * 
     * @return a Gamestate-Object
     */
    GameState getGameState();

    /**
     * Gets the time in milliseconds between each drop of the pieces.
     * 
     * @return in Integer for milliseconds
     */
    int dropTimer();

    /**
     * Moves the tetromino for each tick of the clock, if the position is legal
     * and sticks the tetromino otherwise
     */
    void clockTick();

    /**
     * Sets the current turn.<br>
     * <strong>Only</strong> for tests!<br>
     *
     * @param turn the turn to be set
     */
    void setTurn(Turn turn);

    Turn getTurn();

    void setNextTurn(Piece piece);

    /**
     * <strong>Only</strong> for tests!<br>
     * 
     * @return the Ai bot
     */
    Ai getAi();
}
