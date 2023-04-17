package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class for a grid
 * The class works with a generic type 'E' and implements the interface IGrid
 * 
 * @author Theodor Nissen-Meyer - thnis9544@uib.no
 */
public class Grid<E> implements IGrid<E> {
    private final int rows;
    private final int cols;
    private final List<E> cells;

    /**
     * First of two Grid class constructors.
     * Creates a grid with the amount of given rows and columns
     * Uses second constructor with null as the initial Element for the cells in the
     * grid
     * 
     * @param rows
     * @param cols
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * Second Grid class constructor.
     * Creates a grid with the given rows and cols arguments, and sets each grid
     * cell's value
     * as the argument given for the initial element with type 'E'
     * 
     * @param rows
     * @param cols
     * @param initElement
     */
    public Grid(int rows, int cols, E initElement) {
        this.rows = rows;
        this.cols = cols;

        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException();
        }
        cells = new ArrayList<>(cols * rows);

        for (int i = 0; i < cols * rows; i++) {

            cells.add(initElement);
        }
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> cellList = new ArrayList<GridCell<E>>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CellPosition pos = new CellPosition(i, j);
                E value = get(pos);
                GridCell<E> gridcell = new GridCell<E>(pos, value);
                cellList.add(gridcell);
            }
        }

        return cellList.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
        } else {
            cells.set(locationToIndex(pos), value);
        }
    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Row and column indicied must be within bounds");
        } else {
            return cells.get(locationToIndex(pos));
        }
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.row() >= 0 && pos.col() >= 0 && pos.row() < rows && pos.col() < cols;
    }

    /**
     * This assistive method calculates an index for an iterable list based on the
     * cell's CellPosition
     * 
     * @param pos
     * @return int
     */
    private int locationToIndex(CellPosition pos) {
        return pos.row() + pos.col() * rows;
    }
}
