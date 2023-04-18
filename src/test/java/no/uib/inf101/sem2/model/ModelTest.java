package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

import no.uib.inf101.sem2.model.piece.Turn;

public class ModelTest {

  @Test
  public void nextTurnTest() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board, GameState.ACTIVE_GAME);
    // The placePiece method also makes it the next player's turn
    // Red starts as standard
    model.setTurn(Turn.RED);
    model.placePiece(0);
    assertEquals(Turn.YELLOW, model.getTurn());

  }

  @Test
  public void testDefaultGameState() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board, GameState.ACTIVE_GAME);

    assertEquals(GameState.ACTIVE_GAME, model.getGameState());
  }

  @Test
  public void testClockTickAndGameOver() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board, GameState.ACTIVE_GAME);
    Winner winner = new Winner(board);

    board.set(new CellPosition(0, 1), 'r');
    board.set(new CellPosition(0, 2), 'r');
    board.set(new CellPosition(0, 3), 'r');
    board.set(new CellPosition(0, 4), 'r');

    model.clockTick();

    assertEquals('-', board.get(new CellPosition(0, 0)));
    assertEquals('-', board.get(new CellPosition(0, 2)));
    assertEquals('-', board.get(new CellPosition(0, 3)));
    assertEquals('-', board.get(new CellPosition(0, 4)));

    assertEquals('r', board.get(new CellPosition(1, 1)));
    assertEquals('r', board.get(new CellPosition(1, 2)));
    assertEquals('r', board.get(new CellPosition(1, 3)));
    assertEquals('r', board.get(new CellPosition(1, 4)));

    for (int i = 0; i < board.rows(); i++) {
      model.clockTick();
    }
    assertEquals("Red", winner.findWinner());
    assertEquals(GameState.GAME_OVER, model.getGameState());

  }
}
