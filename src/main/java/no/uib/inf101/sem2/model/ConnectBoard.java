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

    /**
     * Checks if the Character {@code element} exists in the given row
     * 
     * @param row
     * @param element
     * @return true or false
     */
    private boolean elementInRow(int row, Character element){
        for (int col = 0; col < cols(); col++) {
            CellPosition pos = new CellPosition(row, col);
            if ( element.equals( this.get(pos) )) {
                return true;
            }
        }
        return false;
    }

    /**
     * Places the given element in all columns of the given row
     * 
     * @param row
     * @param element
     */
    private void setRow(int row, Character element){
        for (int col = 0; col < cols(); col++) {
            CellPosition pos = new CellPosition(row, col);
            this.set(pos, element);
        }
    }

    /**
     * Copies an entire row from one to another
     * 
     * @param orgRow
     * @param newRow
     */
    private void copyRow(int orgRow, int newRow){
        for (int col = 0; col < cols(); col++) {
            CellPosition posOrgRow = new CellPosition(orgRow, col);
            CellPosition posNewRow = new CellPosition(newRow, col);
            this.set(posNewRow, this.get(posOrgRow));
        }
    }
    
    /**
     * Removes the full rows from the board
     * 
     * @return an int representing the amount of rows removed
     */
    public int removeRows(){
        int rowsRemoved = 0;
        int rowA = rows()-1;
        int rowB = rows()-1;

        for (int colA = rowA; colA >= 0; colA--) {
            for (int colB = rowB; colB >= 0 && !(elementInRow(rowB, '-')); colB--) {
                rowsRemoved ++;
                rowB -= 1;
            }
            if (rowB >= 0){
                copyRow(rowB, rowA);
            }
            else{
                setRow(rowA, '-');
            }
            rowA -= 1;
            rowB -= 1;
            
        }
        return rowsRemoved;
    }

}
