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
    public char[] getCharArrayForRow(int row){
        char [] array = new char[cols()];
        for (int i = 0; i < cols(); i++) {
            array[i] = this.get(new CellPosition(row, i));
        }
        return array;
    }

    public char[] getCharArrayForCol(int col){
        char [] array = new char[rows()];
        for (int i = 0; i < rows(); i++) {
            array[i] = this.get(new CellPosition(i, col));
        }
        return array;
    }

    /**
     * Checks if the Character {@code element} exists in the given row
     * 
     * @param row
     * @param element
     * @return true or false
     */
    private char fourInRow(int row){
        int counter = 0;
        for (int col = 0; col < cols()-1; col++) {
            CellPosition pos1 = new CellPosition(row, col);
            CellPosition pos2 = new CellPosition(row, col + 1);
            if ( this.get(pos1).equals( this.get(pos2) )) {
                counter++;
            }
        }
        if (counter > 3){
        }
        return '-';
    }

    /**
     * Checks if the Character {@code element} exists in the given collumn
     * 
     * @param col
     * @param element
     * @return true or false
     */
    private boolean fourInCol(int col, Character element){
        for (int row = 0; row < rows(); row++) {
            CellPosition pos = new CellPosition(row, col);
            if ( element.equals(this.get(pos))){
                return true;
            }
        }
       return false;
    }

}
