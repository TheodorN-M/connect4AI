package no.uib.inf101.sem2.model;

public class Winner {
    private final ConnectBoard board;

    public Winner(ConnectBoard board) {
        this.board = board;

    }

    public String findWinner() {
        // Find horizontal winner with prettyString method
        if (board.prettyString().contains("rrrr")) {
            return "Red";

        }
        if (board.prettyString().contains("yyyy")) {
            return "Yellow";

        }

        // Find vertical winner with a character array as a string
        for (int col = 0; col < board.cols(); col++) {
            String colAsString = String.valueOf(board.getCharArrayForCol(col));
            if (colAsString.contains("rrrr")) {
                return "Red";

            }
            if (colAsString.contains("yyyy")) {
                return "Yellow";

            }
        }
        // Sjekke om noen har vunnet diagonalt
        char[][] DArray = board.getBoardAs2DArray();
        for (int col = 0; col <= board.cols() - 4; col++) { 
            for (int row = 0; row <= board.rows() - 4; row++) { 

                char s = diagRightWinner(DArray, row, col);
                if (s != 0) {
                    return getWinner(s);
                }
                col += 3;
                s = diagLeftWinner(DArray, row, col);
                col -= 3;
                if (s != 0) {
                    return getWinner(s);
                }
            }
        }
        return null;

    }
    // 
    private char diagRightWinner(char[][] array, int rowStart, int colStart) {
        char a0 = array[rowStart][colStart];
        char b1 = array[rowStart + 1][colStart + 1];
        char c2 = array[rowStart + 2][colStart + 2];
        char d3 = array[rowStart + 3][colStart + 3];

        char[] sequence = {a0, b1, c2, d3};

        if (a0 != '-' && board.threeOrFourInFour(sequence, a0, 4)) {
            return a0;
        }
        return 0;
    }

    private char diagLeftWinner(char[][] array, int rowStart, int colStart) {
        char a0 = array[rowStart][colStart];
        char b1 = array[rowStart + 1][colStart - 1];
        char c2 = array[rowStart + 2][colStart - 2];
        char d3 = array[rowStart + 3][colStart - 3];

        char[] sequence = {a0, b1, c2, d3};

        if (a0 != '-' && board.threeOrFourInFour(sequence, a0, 4)) {
            return a0;

        }
        return 0;
    }

    /**
     * Sets the winner with the given parameter c
     * 
     * @param c 'r' or 'y' for Red or Yellow
     * @return
     */
    private String getWinner(char c) {
        if (c == 'r') {
            return "Red";
        } else if (c == 'y') {
            return "Yellow";
        }
        return null;
    }
}
