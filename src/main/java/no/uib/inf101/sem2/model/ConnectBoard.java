package no.uib.inf101.sem2.model;

// import java.util.Objects;

import no.uib.inf101.sem2.grid.*;

public class ConnectBoard extends Grid<Character> {

    public ConnectBoard(int row, int col) {
        super(row, col, '-');
    }

    /**
     * Gives a printable string representing the current board
     * 
     * @return String
     */
    public String prettyString() {
        String res = "";
        for (int i = 0; i < rows(); i++) {
            String temp = "";
            for (int j = 0; j < cols(); j++) {
                CellPosition pos = new CellPosition(i, j);
                temp += (this.get(pos));
            }
            res += (temp + "\n");
            String.join("\n", new String[] {});
        }

        return res.strip();

    }

    /**
     * Creates a character array for the current characters in a given column
     * 
     * @param col an integer for the column to check
     * @return a char[] with all current characters in the column
     */
    public char[] getCharArrayForCol(int col) {
        char[] array = new char[rows()];
        for (int i = 0; i < rows(); i++) {
            array[i] = this.get(new CellPosition(i, col));
        }
        return array;
    }

    /**
     * Makes the board into a double character array
     * 
     * @return char[][] with all current characters in the board
     */
    public char[][] getBoardAs2DArray() {
        char[][] array = new char[rows()][cols()];
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                array[row][col] = this.get(new CellPosition(row, col));
            }
        }
        return array;
    }

    /**
     * The method drops all pieces on the board one spot down, if possible.
     * 
     * @return true if any pieces were dropped, otherwise false
     */
    public boolean dropPieces() {
        boolean dropping = false;
        for (GridCell<Character> gridCell : this) {
            if (gridCell.value() != '-') {
                CellPosition newCP = new CellPosition(gridCell.pos().row() + 1, gridCell.pos().col());
                if (this.positionIsOnGrid(newCP) && this.get(newCP) == '-') {
                    this.set(gridCell.pos(), '-');
                    this.set(newCP, gridCell.value());
                    dropping = true;
                }
            }
        }
        return dropping;

    }

    /**
     * Counts the amount of a given character and empty spaces in a sequence of four
     * 
     * @param sequence  four characters in a row
     * @param character the character to count
     * @param count     the wished amount of characters
     * @return true if the amount of characters in the sequence equals the
     *         count-parameter and the rest are empty spaces, false otherwise
     */
    public boolean countCharsInFour(char[] sequence, char character, int count) {
        int countDashes = 0;
        int countChars = 0;
        int dashes = -(count - 4);

        for (char c : sequence) {
            if (c == '-') {
                countDashes++;
            }
            if (c == character) {
                countChars++;
            }
        }
        if (countDashes == dashes && countChars == count) {
            return true;
        }
        return false;
    }

    /**
     * Clears the board.
     * 
     * <strong>Only</strong> for tests!
     */
    public void clear() {
        for (GridCell<Character> gridCell : this) {
            set(gridCell.pos(), '-');
        }
    }

    /**
     * Checks if the board is full
     * 
     * @return
     */
    public boolean isFull() {
        for (GridCell<Character> gridCell : this) {
            if (gridCell.value() == '-') {
                return false;
            }
        }
        return true;
    }

}
