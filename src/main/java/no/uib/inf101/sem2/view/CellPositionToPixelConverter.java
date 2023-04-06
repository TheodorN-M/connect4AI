package no.uib.inf101.sem2.view;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;


public class CellPositionToPixelConverter {
  private final Rectangle2D box;
  private final GridDimension gd;
  private final double margin;

  /**
   * Constructs a converter-object 
   * 
   * @param box Rectangle2D
   * @param gd GridDimension
   * @param margin around the rectangle
   */
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }
  /**
   * Converts the given CellPosition to coordinates in the view
   * 
   * @param cp CellPosition
   * @return a Ellipse2D-object ready for display in the window
   */
  public Ellipse2D getBoundsForHole(CellPosition cp){
    int rows = gd.rows();
    int cols = gd.cols();

    double width = box.getWidth();
    double height = box.getHeight();

    double cellWidth = (width - (this.margin * (cols +1))) / cols;              
    double cellHeight = (height - (this.margin * (rows +1))) / rows;               

    double cellX = box.getX() + (this.margin * (cp.col() + 1)) + (cellWidth * cp.col());    
    double cellY = box.getY() + (this.margin * (cp.row() + 1)) + (cellHeight * cp.row());

    return new Ellipse2D.Double(cellX, cellY, cellWidth, cellHeight);         
  }
}
