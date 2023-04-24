package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.ai.Ai;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;

public interface ControllableModel {
    /**
     * 
     * Places a piece in the board in the given parameter col
     * 
     * @param col an integer representing the column to place a piece
     */
    void placePiece(int col);

    /** */
    GridDimension getDimension();

    /**
     * Returns the current GameState, {@code ACTIVE_GAME}, {@code GAME_OVER} or {@code AI_ACTIVE}
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
     * Calls upon the method dropPieces to drop all pieces not yet 
     * 
     * @see no.uib.inf101.sem2.model.ConnectBoard#dropPieces()
     */
    void clockTick();

    /**
     * Sets the current turn.<br>
     * <strong>Only</strong> for tests!<br>
     *
     * @param turn the turn to be set
     */
    void setTurn(Turn turn);

    /**
     * Gets whos turn it is currently
     * 
     * @return a Turn-Object, {@code RED} or {@code YELLOW}
     */
    Turn getTurn();

    /**
     * Sets the turn to the other player
     * 
     * @param piece the previous' players piece object
     */
    void setNextTurn(Piece piece);

    /**
     * <strong>Only</strong> for tests!<br>
     * 
     * @return the Ai bot object
     */
    Ai getAi();
}
