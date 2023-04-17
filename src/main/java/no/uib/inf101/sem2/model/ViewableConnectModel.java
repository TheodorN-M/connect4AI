package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewableConnectModel {
  /**
   * Returns a GridDimension-Object
   * 
   * @return GridDimension
   */
  GridDimension getDimension();

  /**
   * Iterates over all holes on the board.
   * Returns an Object that gives all positions on the board with red, yellow or
   * no piece
   * 
   * @return an iterable object with the holes on the board
   */
  Iterable<GridCell<Character>> getHolesOnBoard();

  /**
   * Returns the current GameState, {@code ACTIVE_GAME} or {@code GAME_OVER}
   * 
   * @return a Gamestate-Object
   */
  GameState getGameState();

  /**
   * 
   * @return Who's turn it is as a String
   */
  String getTurnAsString();

  /**
   * This happens every time the clock ticks
   */
  void clockTick();

  /**
   * 
   * @return the winner as a String
   */
  String getWinnerString();

}
