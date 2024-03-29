package no.uib.inf101.sem2.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.view.CellPositionToPixelConverter;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

public class TestCellPositionToPixelConverter {
  @Test
  public void sanityTest() {
    GridDimension gd = new ConnectBoard(3, 4);
    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
        new Rectangle2D.Double(29, 29, 340, 240), gd, 30);
    Ellipse2D expected = new Ellipse2D.Double(214, 129, 47.5, 40);
    assertEquals(expected, converter.getBoundsForHole(new CellPosition(1, 2)));
  }

}
