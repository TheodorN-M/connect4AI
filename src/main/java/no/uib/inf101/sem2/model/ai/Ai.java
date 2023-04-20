package no.uib.inf101.sem2.model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.model.ConnectModel;

public class Ai {
    ConnectBoard board;
    ConnectModel model;
    Random rnd = new Random();
    int bottomRow;
    char empty = '-';

    public Ai(ConnectBoard board, ConnectModel model) {
        this.model = model;
        this.board = board;
        this.bottomRow = board.rows()-1;
    }

    public void aiPlacePiece() {

        int col = findLucrativeSpot();

        CellPosition pos = new CellPosition(0, col);

        while (board.get(pos) != empty) {
            pos = new CellPosition(0, rnd.nextInt(board.cols()));
        }

        model.placePiece(col);

    }

    public int findLucrativeSpot() {
        char[][] holes = board.getBoardAs2DArray();

        AiPlacement vertical = findColForCol();
        AiPlacement horizontal = findColForRow(holes);

        AiPlacement downDiag = findColForDownDiag(holes, 'y');
        AiPlacement hinderRedDownDiag = findColForDownDiag(holes, 'r');

        AiPlacement upDiag = findColForUpDiag(holes, 'y');
        AiPlacement hinderRedUpDiag = findColForUpDiag(holes, 'r');

        AiPlacement hinderRedH = hinderRedHorizontally(holes);
        AiPlacement hinderRedV = hinderRedVertically(holes);

        int col = bestAiMove(vertical, horizontal, hinderRedH, hinderRedV,
                downDiag, upDiag, hinderRedDownDiag, hinderRedUpDiag);

        if (col != -1) {
            return col;
        }
        System.out.println("** Random column chosen ** \n");
        return rnd.nextInt(board.cols());

    }

    private AiPlacement findColForCol() {
        int optimalCol = -1;
        int score = 1;


        for (int col = 0; col < board.cols(); col++) {
            String currentCol = String.valueOf(board.getCharArrayForCol(col));

            if (currentCol.contains("-yyy")) {
                optimalCol = col;
                score = 6;
                return new AiPlacement(optimalCol, score);
            }
            if (currentCol.contains("--yy")) {
                optimalCol = col;
                score = 3;

            } else if (currentCol.contains("---y")) {
                if (score != 3){
                    optimalCol = col;
                    score = 1;
                }

            }
            if (score > 1){
                return new AiPlacement(optimalCol, score);
            }
        }

        return null;
    }


    private AiPlacement findColForRow(char[][] array) {
        int score = 1;
        for (int row = 0; row < board.rows(); row++) {
            char[] currentRow = array[row];

            String currentRowString = String.valueOf(currentRow);

            if (currentRowString.contains("-y-y-")){

                int colIndex = currentRowString.indexOf("-y-y-");
                int colToReturn = colIndex + 2;

                score = 3;
                if ((row != bottomRow && array[row + 1][colToReturn] != empty && array[row+1][colIndex] != empty && array[row+1][colIndex+4] != empty) || row == bottomRow){
                    return new AiPlacement(colToReturn, score);
                }

            }


            if (currentRowString.contains("--yy-")){
                int colIndex = currentRowString.indexOf("--yy-");
                int colToReturn = colIndex + 1;

                score = 4;
                if ((row != bottomRow && array[row + 1][colToReturn] != empty && array[row+1][colIndex] != empty && array[row+1][colIndex+4] != empty) || row == bottomRow){
                    return new AiPlacement(colToReturn, score);
                }
            }

            if (currentRowString.contains("-yy--")){
                int colIndex = currentRowString.indexOf("-yy--");
                int colToReturn = colIndex + 3;

                score = 4;
                if ((row != bottomRow && array[row + 1][colToReturn] != empty && array[row+1][colIndex] != empty && array[row+1][colIndex+4] != empty) || row == bottomRow){
                    return new AiPlacement(colToReturn, score);
                }
            }



            for (int col = 0; col < board.cols() - 3; col++) {

                char[] fourCols = { currentRow[col], currentRow[col + 1], currentRow[col + 2], currentRow[col + 3] };


                if (board.countCharsInFour(fourCols, 'y', 3)) {
                    int colToReturn = String.valueOf(fourCols).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 6);
                    }
                }

                if (board.countCharsInFour(fourCols, 'y', 2)) {
                    int colToReturn = String.valueOf(fourCols).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 3);
                    }
                }

            }

        }
        return null;
    }

    private AiPlacement hinderRedHorizontally(char[][] array) {
        for (int row = 0; row < board.rows(); row++) {
            char[] currentRow = array[row];

            String currentRowString = String.valueOf(currentRow);

            for (int col = 0; col < board.cols() - 3; col++) {
                char[] fourCols = { currentRow[col], currentRow[col + 1], currentRow[col + 2], currentRow[col + 3] };

                if (board.countCharsInFour(fourCols, 'r', 3)) {
                    int colToReturn = String.valueOf(fourCols).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 4);
                    }
                }

            }
            if (currentRowString.contains("-r-r-")){

                int colIndex = currentRowString.indexOf("-r-r-");
                int colToReturn = colIndex + 2;

                int score = 3;
                if ((row != bottomRow && array[row + 1][colToReturn] != empty && array[row+1][colIndex] != empty && array[row+1][colIndex+4] != empty) || row == bottomRow){
                    return new AiPlacement(colToReturn, score);
                }

            }
        }
        return null;
    }

    private AiPlacement hinderRedVertically(char[][] array) {

        for (int col = 0; col < board.cols(); col++) {
            String currentCol = String.valueOf(board.getCharArrayForCol(col));

            if (currentCol.contains("-rrr")) {
                return new AiPlacement(col, 5);
            }
        }
        return null;
    }

    private AiPlacement findColForUpDiag(char[][] array, char yORr) {

        for (int row = 3; row < board.rows(); row++) {
            for (int col = 0; col <= board.cols() - 4; col++) {

                int colToReturn = -1;

                char a0 = array[row][col];
                char b1 = array[row - 1][col + 1];
                char c2 = array[row - 2][col + 2];
                char d3 = array[row - 3][col + 3];
                char e4 = empty;

                char[] sequence = { a0, b1, c2, d3 };

                if (board.countCharsInFour(sequence, yORr, 3)) {
                    int sequenceIndex = String.valueOf(sequence).indexOf(empty);
                    colToReturn = sequenceIndex + col;

                    e4 = array[row - sequenceIndex + 1][colToReturn]; 

                    if (e4 != empty && yORr == 'y') {
                        return new AiPlacement(colToReturn, 6);
                    }
                    if (e4 != empty && yORr == 'r') {
                        return new AiPlacement(colToReturn, 5);
                    }
                }
                if (board.countCharsInFour(sequence, 'y', 2)) {
                    colToReturn = String.valueOf(sequence).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 2);
                    }
                }
                if (board.countCharsInFour(sequence, 'y', 1)) {
                    colToReturn = String.valueOf(sequence).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 1);
                    }
                }
            }
        }
        return null;
    }

    private AiPlacement findColForDownDiag(char[][] array, char yORr) {
        for (int row = 0; row <= board.rows() - 4; row++) {
            for (int col = 0; col <= board.cols() - 4; col++) {
                int colToReturn = -1;

                char a0 = array[row][col];
                char b1 = array[row + 1][col + 1];
                char c2 = array[row + 2][col + 2];
                char d3 = array[row + 3][col + 3];
                char e4 = empty;

                char[] sequence = { a0, b1, c2, d3 };

                if (board.countCharsInFour(sequence, yORr, 3)) {
                    int sequenceIndex = String.valueOf(sequence).indexOf(empty);
                    colToReturn = sequenceIndex + col;
                    e4 = array[row + sequenceIndex + 1][colToReturn];

                    if (e4 != empty && yORr == 'y') {
                        return new AiPlacement(colToReturn, 6);
                    }
                    if (e4 != empty && yORr == 'r') {
                        return new AiPlacement(colToReturn, 5);
                    }
                }
                if (board.countCharsInFour(sequence, 'y', 2)) {
                    colToReturn = String.valueOf(sequence).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 2);
                    }
                }
                if (board.countCharsInFour(sequence, 'y', 1)) {
                    colToReturn = String.valueOf(sequence).indexOf(empty) + col;
                    if (row != bottomRow && array[row+1][colToReturn] != empty || row == bottomRow){
                        return new AiPlacement(colToReturn, 1);
                    }
                }
            }
            
        }
        return null;
    }

    private int bestAiMove(AiPlacement one, AiPlacement two, AiPlacement three, AiPlacement four, AiPlacement five,
            AiPlacement six, AiPlacement seven, AiPlacement eight) {
        List<AiPlacement> options = new ArrayList<>();

        if (one != null) {
            options.add(one);
        }
        if (two != null) {
            options.add(two);
        }
        if (three != null) {
            options.add(three);
        }
        if (four != null) {
            options.add(four);
        }
        if (five != null) {
            options.add(five);
        }
        if (six != null) {
            options.add(six);
        }
        if (seven != null) {
            options.add(seven);
        }
        if (eight != null) {
            options.add(eight);
        }

        int highestScore = -1;
        int optimalCol = -1;

        for (int i = 0; i < options.size(); i++) {
            AiPlacement temp = options.get(i);

            if (temp.score() > highestScore) {
                highestScore = temp.score();
                optimalCol = temp.col();
            }
        }
        return optimalCol;

    }

}
