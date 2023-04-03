package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;


public interface ViewableConnectModel {
/**
  * Returns a GridDimension-Object
  * @return GridDimension
  */
  GridDimension getDimension();

  /**
   * Iterates over all tiles on the board. 
   * Returns an Object that gives all positions on the board with tilhørende symbol
   * 
   * @return an iterable object with the tiles on the board
   */
  Iterable<GridCell<Character>> getTilesOnBoard();


  /**
   * Returns the current GameState, {@code ACTIVE_GAME} or {@code GAME_OVER}
   * @return a Gamestate-Object
   */
  GameState getGameState();

  String getTurnAsString();


  /**
   * This happens every time the clock ticks
   */
  void clockTick();


}


