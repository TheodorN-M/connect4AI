package no.uib.inf101.sem2.model;

public class Winner {
    private ConnectBoard board;

    public Winner(ConnectBoard board){
        this.board = board;

    }


    public String findWinner(){
        // Find horizontal winner with prettyString method
        if (board.prettyString().contains("rrrr")){
            return "Red";

        }
        if (board.prettyString().contains("yyyy")){
            return "Yellow";

        }

        // Find vertical winner with a character array as a string
        for (int col = 0; col < board.cols(); col++) {
            String colAsString = String.valueOf(board.getCharArrayForCol(col));
            if (colAsString.contains("rrrr")){
                return "Red";

            }
            if (colAsString.contains("yyyy")){
                return "Yellow";

            }
        }
        // Sjekke om noen har vunnet diagonalt
        char[][] DArray = board.getBoardAs2DArray();
        for (int col = 0; col <= board.cols()-4; col++) { // 0 til og med 3 
            for (int row = 0; row <= board.rows()-4; row++) { // 0 til og med 2

                String s = diagRightWinner(DArray, row, col);
                if (s != null) {
                    return s;
                }
                col += 3;
                s = diagLeftWinner(DArray, row, col);
                col -= 3;
                if (s != null){
                    return s;
                }
            }
        }
        return null;
        

    }
    private String diagRightWinner(char[][] board, int rowStart, int colStart){
        char a0 = board[rowStart][colStart];
        char b1 = board[rowStart +1][colStart +1];
        char c2 = board[rowStart +2][colStart +2];
        char d3 = board[rowStart +3][colStart +3];

        if (a0 == b1 && c2 == d3 && a0 == c2 && a0 != '-'){
            return getWinner(a0);
            
        }
        return null;
    }

    private String diagLeftWinner(char[][] board, int rowStart, int colStart){
        char a0 = board[rowStart][colStart];
        char b1 = board[rowStart +1][colStart -1];
        char c2 = board[rowStart +2][colStart -2];
        char d3 = board[rowStart +3][colStart -3];

        if (a0 == b1 && c2 == d3 && a0 == c2 && a0 != '-'){
            return getWinner(a0);

        }
        return null;
    }

    /**
    * Sets the winner with the given parameter c
    * @param c 'r' or 'y' for Red or Yellow
    * @return 
    */
    private String getWinner(char c){
        if (c == 'r'){
            return "Red";
        }
        else if (c == 'y'){
            return "Yellow";
        }
        return null;
    }
}
