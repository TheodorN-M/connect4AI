package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

import no.uib.inf101.sem2.model.piece.Turn;

public class ModelTest {

  @Test
  public void nextTurnTest() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board);
    // The placePiece method also makes it the next player's turn
    // Red starts as standard
    model.setTurn(Turn.RED);
    model.placePiece(0);
    assertEquals(Turn.YELLOW, model.getTurn());

  }

  @Test
  public void placePieceTest() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board);
    model.setTurn(Turn.RED);

    List<GridCell<Character>> holes = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes.add(gridCell);
    }

    assertTrue(holes.contains(new GridCell<>(new CellPosition(0, 2), '-')));
    assertEquals(Turn.RED, model.getTurn());

    model.placePiece(2);

    List<GridCell<Character>> holes2 = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes2.add(gridCell);
    }

    assertFalse(holes.equals(holes2));
    assertEquals(Turn.YELLOW, model.getTurn());

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
  public void dropPiecesTest() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board);
    model.setTurn(Turn.RED);
    for (int col = 0; col < 5; col++) {
      model.placePiece(col);
    }
    board.dropPieces();

    List<GridCell<Character>> holes = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes.add(gridCell);
    }
    for (int i = 0; i < 5; i++) {
      assertFalse(holes.contains(new GridCell<>(new CellPosition(1, i), '-')));
    }

  }

  @Test
  public void dropPiecesMultipleTimesTest() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board);
    for (int col = 0; col < 5; col++) {
      model.placePiece(col);
    }
    for (int i = 0; i < 5; i++) {
      board.dropPieces();
    }

    List<GridCell<Character>> holes = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes.add(gridCell);
    }
    for (int i = 0; i < 5; i++) {
      assertFalse(holes.contains(new GridCell<>(new CellPosition(5, i), '-')));
    }
  }

  @Test
  public void dropPiecesOnTopOfOtherPiecesAndNotUnderBoardLimitTest() {
    ConnectBoard board = new ConnectBoard(6, 7);
    ConnectModel model = new ConnectModel(board);
    model.setTurn(Turn.RED);

    board.set(new CellPosition(5, 3), 'r');
    for (int i = 2; i < 5; i++) {
      model.placePiece(i);
    }

    for (int i = 0; i < 20; i++) {
      board.dropPieces();
    }

    List<GridCell<Character>> holes = new ArrayList<>();
    for (GridCell<Character> gridCell : model.getHolesOnBoard()) {
      holes.add(gridCell);
    }
    assertTrue(holes.contains(new GridCell<>(new CellPosition(5, 2), 'r')));
    assertTrue(holes.contains(new GridCell<>(new CellPosition(5, 3), 'r')));
    assertTrue(holes.contains(new GridCell<>(new CellPosition(5, 4), 'r')));

    assertTrue(holes.contains(new GridCell<>(new CellPosition(4, 3), 'y')));

  }

}
