package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;




import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

import no.uib.inf101.sem2.model.ConnectBoard;

import no.uib.inf101.sem2.model.Winner;


public class WinnerTest {
  @Test
  public void testRedHorizontalWinner(){
    ConnectBoard board = new ConnectBoard(6, 7);
    
    for (int i = 0; i < 4; i++) {
        board.set(new CellPosition(5, i), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
    }


  @Test
  public void testYellowHorizontalWinner(){
    ConnectBoard board = new ConnectBoard(6, 7);
    
    for (int i = 0; i < 4; i++) {
        board.set(new CellPosition(5, i), 'y');
    }
    Winner winner = new Winner(board);
    assertEquals("Yellow", winner.findWinner());
    }

  @Test
  public void testVerticalWinner(){
    ConnectBoard board = new ConnectBoard(6, 7);
    
    for (int i = 0; i < 4; i++) {
        board.set(new CellPosition(i, 0), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
    }

  @Test
  public void testRightDiagonallyWinner(){
    ConnectBoard board = new ConnectBoard(6, 7);
    
    for (int i = 0; i < 4; i++) {
        board.set(new CellPosition(i, i), 'r');
    }
    Winner winner = new Winner(board);
    assertEquals("Red", winner.findWinner());
    }
    @Test
    public void testLeftDiagonallyWinner(){
      ConnectBoard board = new ConnectBoard(6, 7);
      
      for (int i = 5; i > 1; i--) {
          board.set(new CellPosition(i, i), 'r');
      }
      Winner winner = new Winner(board);
      assertEquals("Red", winner.findWinner());
      }
}
