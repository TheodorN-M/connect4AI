package no.uib.inf101.sem2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.model.ConnectModel;
import no.uib.inf101.sem2.model.piece.Turn;

public class ModelTest {
  ConnectBoard board = new ConnectBoard(6, 7);
  ConnectModel model = new ConnectModel(board);
  Turn turn = Turn.RED;


  @Test
  public void nextTurnTest(){
    // The placePiece method also makes it the next player's turn
    turn = Turn.RED;
    model.placePiece(0);
    assertEquals(Turn.YELLOW, model.getTurn());

  }
    
  @Test
  public void placePieceTest() {
    List<GridCell<Character>> holes = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes.add(gridCell);
    }

    assertTrue(holes.contains(new GridCell<>(new CellPosition(0, 2), '-')));
    model.placePiece(2);

    List<GridCell<Character>> holes2 = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes2.add(gridCell);
    }

    assertFalse(holes.equals(holes2));
    assertTrue(holes2.contains(new GridCell<>(new CellPosition(0, 2), 'r')));

    model.placePiece(5);
    List<GridCell<Character>> holes3 = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes3.add(gridCell);
    }
    System.out.println(holes3);
    assertFalse(holes3.equals(holes2));
    assertTrue(holes3.contains(new GridCell<>(new CellPosition(0, 5), 'y')));

  }

  @Test
  public void dropPiecesTest(){
    
  }

}
