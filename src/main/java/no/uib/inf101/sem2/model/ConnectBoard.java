package no.uib.inf101.sem2.model;


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
    public String prettyString(){
        String res = "";
        for (int i = 0; i < rows(); i++) {
            String temp = "";
            for (int j = 0; j < cols(); j++) {
                CellPosition pos = new CellPosition(i, j);
                temp += (this.get(pos));
            }
            res += (temp + "\n");
            String.join("\n", new String[]{});
        }

        return res.strip();

    }

    public char[] getCharArrayForCol(int col){
        char [] array = new char[rows()];
        for (int i = 0; i < rows(); i++) {
            array[i] = this.get(new CellPosition(i, col));
        }
        return array;
    }

    public char[][] getBoardAs2DArray(){
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
     * @return true if any pieces were dropped, otherwise false 
     */
    public boolean dropPieces() {
        boolean dropping = false;
        for (GridCell<Character> gridCell : this) {
            if (gridCell.value() != '-'){
                CellPosition newCP = new CellPosition(gridCell.pos().row() + 1, gridCell.pos().col());
                if (this.positionIsOnGrid(newCP) && this.get(newCP) == '-'){
                    this.set(gridCell.pos(), '-');
                    this.set(newCP, gridCell.value());
                    dropping = true;
                }
            }
        }
        return dropping;

    }

}
