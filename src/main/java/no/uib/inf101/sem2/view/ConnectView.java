package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.ViewableConnectModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.Dimension;

public class ConnectView extends JPanel {
  private ColorTheme theme;
  private final ViewableConnectModel view;
  private static final double OUTERMARGIN = 20;
  private static final double INNERMARGIN = 20;

  public ConnectView(ViewableConnectModel view) {
    this.view = view;
    this.theme = new DefaultColorTheme();
    this.setBackground(theme.getBackgroundColor());

    this.setFocusable(true);
    this.setPreferredSize(new Dimension(700, 600));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
  }

  /**
   * Draws the connect4 game
   * 
   * @param g Graphics
   */
  private void drawGame(Graphics2D g) {

    Rectangle2D box = new Rectangle2D.Double(
        OUTERMARGIN,
        OUTERMARGIN,
        this.getWidth() - OUTERMARGIN * 2,
        this.getHeight() - OUTERMARGIN * 2);

    g.draw(box);
    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, view.getDimension(), INNERMARGIN);

    drawPiecesAndHolesInBoard(g, view.getHolesOnBoard(), converter, theme);

    if (view.getGameState() == GameState.GAME_OVER) {
      drawGameOver(g);
    }
    g.setFont(this.theme.getTurnFont());
    g.setColor(this.theme.getFontColor());
    Inf101Graphics.drawCenteredString(g, view.getTurnAsString() + "'s turn. Click desired column",
        0, 0, getWidth(), OUTERMARGIN);

  }

  /**
   * Draws each 'hole' and pieces in the playing board
   * 
   * @param g         graphics
   * @param table     iterable list of the cells to be drawn
   * @param converter
   * @param theme     for colors in the cells
   */
  private void drawPiecesAndHolesInBoard(Graphics2D g, Iterable<GridCell<Character>> table,
                                         CellPositionToPixelConverter converter, ColorTheme theme) {

    for (GridCell<Character> gridCell : table) {
      CellPosition pos = gridCell.pos();

      Ellipse2D ellipse = converter.getBoundsForHole(pos);

      g.setColor(theme.getCellColor(gridCell.value()));

      g.fill(ellipse);

    }

  }

  /**
   * Draws an overlay when the round is over
   * 
   * @param g graphics
   */
  private void drawGameOver(Graphics2D g) {
    g.setColor(this.theme.getGameOverFadedColor());
    g.fillRect(0, 0, getWidth(), getHeight());

    g.setFont(this.theme.getFont());
    g.setColor(this.theme.getFontColor());
    Inf101Graphics.drawCenteredString(g, "Game over, " + view.getWinnerString() + " wins!",
        0, 0, getWidth(), getHeight());

  }

}