package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class WinnerTest {
  @Test
  public void testRedHorizontalWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    for (int i = 0; i < 4; i++) {
      board.set(new CellPosition(5, i), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
  }

  @Test
  public void testYellowHorizontalWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    for (int i = 0; i < 4; i++) {
      board.set(new CellPosition(5, i), 'y');
    }
    Winner winner = new Winner(board);
    assertEquals("Yellow", winner.findWinner());
  }

  @Test
  public void testVerticalWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    for (int i = 0; i < 4; i++) {
      board.set(new CellPosition(i, 0), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
  }

  @Test
  public void testRightDiagonallyTopWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    for (int i = 0; i < 4; i++) {
      board.set(new CellPosition(i, i), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
  }

  @Test
  public void testLeftDiagonallyTopWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    for (int i = 5; i > 1; i--) {
      board.set(new CellPosition(i, i), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
  }

  @Test
  public void testRightDiagonallyBottomWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    board.set(new CellPosition(2, 0), 'r');
    board.set(new CellPosition(3, 1), 'r');
    board.set(new CellPosition(4, 2), 'r');
    board.set(new CellPosition(5, 3), 'r');

    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
  }

  @Test
  public void testLeftDiagonallyBottomWinner() {
    ConnectBoard board = new ConnectBoard(6, 7);

    board.set(new CellPosition(2, board.cols() - 1), 'r');
    board.set(new CellPosition(3, board.cols() - 2), 'r');
    board.set(new CellPosition(4, board.cols() - 3), 'r');
    board.set(new CellPosition(5, board.cols() - 4), 'r');

    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
  }

  @Test
    public void biggerBoardTest(){
        ConnectBoard board = new ConnectBoard(20, 20);
        ConnectModel model = new ConnectModel(board);
        Winner winner = new Winner(board);


        board.set(new CellPosition(14, 14), 'r');
        board.set(new CellPosition(14, 15), 'r');
        board.set(new CellPosition(14, 16), 'r');
        board.set(new CellPosition(14, 17), 'r');
        
        assertFalse(board.positionIsOnGrid(new CellPosition(21, 21)));
        assertEquals("Red", winner.findWinner());

    }
}
