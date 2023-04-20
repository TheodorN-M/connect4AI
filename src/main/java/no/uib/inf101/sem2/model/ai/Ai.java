package no.uib.inf101.sem2.model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.model.ConnectModel;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;

public class Ai {
    ConnectBoard board;
    ConnectModel model;
    Random rnd = new Random();

    public Ai(ConnectBoard board, ConnectModel model) {
        this.model = model;
        this.board = board;
    }

    public void aiPlacePiece() {

        CellPosition pos = new CellPosition(0, findLucrativeSpot());

        while(board.get(pos) != '-'){
            pos = new CellPosition(0, rnd.nextInt(board.cols()));
        }

        model.placePiece(findLucrativeSpot());

        // board.set(new CellPosition(0, rnd.nextInt(board.cols())), 'y');
            
        // model.setNextTurn(new Piece('y', pos));


    }

    public int findLucrativeSpot() {
        char[][] holes = board.getBoardAs2DArray();
        // char[][] holesFlipped = board.getFlippedBoardAs2DArray();

        AiPlacement vertical = findColForCol();
        AiPlacement horizontal = findColForRow(holes);

        AiPlacement downDIag = findColForDownDiag(holes);
        AiPlacement upDiag = findColForUpDiag(holes);

        AiPlacement hinderRedH = hinderRedHorizontally(holes);
        AiPlacement hinderRedV = hinderRedVertically(holes);

        AiPlacement hinderRedDownDiag = hinderRedDownDiag(holes);
        AiPlacement hinderRedUpDiag = hinderRedUpDiag(holes);

        
        int col = bestAiMove(vertical, horizontal, hinderRedH, hinderRedV, 
                            downDIag, upDiag, hinderRedDownDiag, hinderRedUpDiag);

        if (col != -1){
            return col;
        }
        return rnd.nextInt(board.cols());

        // return downDIag.col();

    }

    private AiPlacement findColForCol() {
        int optimalCol = -1;
        int score = 1;
        boolean intChanged = false;

        for (int col = 0; col < board.cols(); col++) {
            String currentCol = String.valueOf(board.getCharArrayForCol(col));

            if (currentCol.contains("-yyy")) {
                optimalCol = col;
                score = 5;
                intChanged = true;
                return new AiPlacement(optimalCol, score);
            }
            if (currentCol.contains("-y")) {
                optimalCol = col;
                score = 2;
                intChanged = true;
            }
            if (currentCol.contains("-yy")) {
                optimalCol = col;
                score = 3;
                intChanged = true;
            }
        }
        if (intChanged) {
            // if (board.get(new CellPosition(0, optimalCol)) == '-') {
                return new AiPlacement(optimalCol, score);
            // }
        }
        return null;
    }

    private AiPlacement findColForRow(char[][] array) {
        int optimalCol = -1;
        int score = 1;
        for (int row = 0; row < board.rows(); row++) {
            char[] currentRow = array[row];


            String currentRowString = String.valueOf(currentRow);
            if (currentRowString.contains("-y")) {
                score = 2;
            }
            if (currentRowString.contains("y-")) {
                score = 2;
            }
            if (currentRowString.contains("-yy")) {
                score = 3;
            }
            if (currentRowString.contains("yy-")) {
                score = 3;
            }
            if (currentRowString.contains("-yyy")) {
                score = 5;
            }
            if (currentRowString.contains("yyy-")) {
                score = 5;
            }

            for (int col = 0; col < board.cols() - 1; col++) {
                if (col != 0 && currentRow[col] == 'y' && currentRow[col - 1] == '-') {
                    return new AiPlacement(col-1, score);
                } else if (currentRow[col] == 'y' && currentRow[col + 1] == '-') {
                    return new AiPlacement(col+1, score);
                    
                }
            }
            for (int col = 0; col < board.cols() - 3; col++) {

                char[] fourCols = {currentRow[col], currentRow[col+1], currentRow[col+2], currentRow[col+3]};

                if(threeInFour(fourCols, 'y')){
                    return new AiPlacement(String.valueOf(fourCols).indexOf('-')+ col, 5);
                }
            }
        }
        return null;
    }
        

    private AiPlacement hinderRedHorizontally(char[][] array){
        for (int row = 0; row < board.rows(); row++) {
          char[] currentRow = array[row];

            for (int col = 0; col < board.cols() - 3; col++) {
                char[] fourCols = {currentRow[col], currentRow[col+1], currentRow[col+2], currentRow[col+3]};

                if(threeInFour(fourCols, 'r')){
                    return new AiPlacement(String.valueOf(fourCols).indexOf('-')+ col, 4);
                }

            }
        }
        return null;
    }

    private AiPlacement hinderRedVertically(char[][] array){

        for (int col = 0; col < board.cols(); col++) {
            String currentCol = String.valueOf(board.getCharArrayForCol(col));

            if (currentCol.contains("-rrr")) {
                return new AiPlacement(col, 4);
            }
        }
        return null;
    }

    
    private AiPlacement findColForUpDiag(char[][] array){
        for (int row = 3; row < board.rows(); row++) {
            for (int col = 0; col <= board.cols()-4; col++) {
                
                int colToReturn = -1;

                char a0 = array[row][col];
                char b1 = array[row - 1][col + 1];
                char c2 = array[row - 2][col + 2];
                char d3 = array[row - 3][col + 3];
                char e4 = '-';

                char[] sequence = {a0, b1, c2, d3};

                if (threeInFour(sequence, 'y')){

                    int sequenceIndex = String.valueOf(sequence).indexOf('-');
                    colToReturn = sequenceIndex + col;

                    e4 = array[row - sequenceIndex+1][colToReturn];

                    if (e4 != '-'){
                        return new AiPlacement(colToReturn, 5);
                    }
                }
            }
        }
        return null;
    }
    
    private AiPlacement findColForDownDiag(char[][] array){
        for (int row = 0; row <= board.rows()-4; row++) {
            for (int col = 0; col <= board.cols()-4; col++) {
                int colToReturn = -1;

                
                char a0 = array[row][col];
                char b1 = array[row + 1][col + 1];
                char c2 = array[row + 2][col + 2];
                char d3 = array[row + 3][col + 3];
                char e4 = '-';

                char[] sequence = {a0, b1, c2, d3};
                
                if (threeInFour(sequence, 'y')){
                    int sequenceIndex = String.valueOf(sequence).indexOf('-');
                    colToReturn = sequenceIndex + col;
                    e4 = array[row + sequenceIndex+1][colToReturn];

                    if (e4 != '-'){
                        return new AiPlacement(colToReturn, 5);
                    }
                }
            }
        }
        return null;
    }

    private AiPlacement hinderRedUpDiag(char[][] array){
        for (int row = 3; row < board.rows(); row++) {
            for (int col = 0; col <= board.cols()-4; col++) {
                
                int colToReturn = -1;

                char a0 = array[row][col];
                char b1 = array[row - 1][col + 1];
                char c2 = array[row - 2][col + 2];
                char d3 = array[row - 3][col + 3];
                char e4 = '-';

                char[] sequence = {a0, b1, c2, d3};

                if (threeInFour(sequence, 'r')){

                    int sequenceIndex = String.valueOf(sequence).indexOf('-');
                    colToReturn = sequenceIndex + col;

                    e4 = array[row - sequenceIndex+1][colToReturn];

                    if (e4 != '-'){
                        return new AiPlacement(colToReturn, 4);
                    }
                }
            }
        }
        return null;
    }

    private AiPlacement hinderRedDownDiag(char[][] array){
        for (int row = 0; row <= board.rows()-4; row++) {
            for (int col = 0; col <= board.cols()-4; col++) {
                int colToReturn = -1;

                
                char a0 = array[row][col];
                char b1 = array[row + 1][col + 1];
                char c2 = array[row + 2][col + 2];
                char d3 = array[row + 3][col + 3];
                char e4 = '-';

                char[] sequence = {a0, b1, c2, d3};
                
                if (threeInFour(sequence, 'r')){
                    int sequenceIndex = String.valueOf(sequence).indexOf('-');
                    colToReturn = sequenceIndex + col;
                    e4 = array[row + sequenceIndex+1][colToReturn];

                    if (e4 != '-'){
                        return new AiPlacement(colToReturn, 4);
                    }
                }
            }
        }
        return null;
    }


    private int bestAiMove(AiPlacement one, AiPlacement two, AiPlacement three, AiPlacement four, AiPlacement five, AiPlacement six, AiPlacement seven, AiPlacement eight) {
        List<AiPlacement> options = new ArrayList<>();        
        if (one != null){
            options.add(one);
        }        if (two != null){
            options.add(two);
        }
        if (three != null){
            options.add(three);
        }
        if(four != null){
            options.add(four);
        }
        if(five != null){
            options.add(five);
        }
        if(six != null){
            options.add(six);
        }
        if(seven != null){
            options.add(seven);
        }
        if(eight != null){
            options.add(eight);
        }


            int highestScore = -1;
            int optimalCol = -1;

            for (int i = 0; i < options.size(); i++) {
                AiPlacement temp = options.get(i);
                
                if (temp.score() > highestScore){
                    highestScore = temp.score();
                    optimalCol = temp.col();
                }
            }
            return optimalCol;
        
    }

    private boolean threeInFour(char [] sequence, char character){
        int countDashes = 0;
        int countChars = 0;

        for (char c : sequence) {
            if(c == '-'){
                countDashes++;
            }
            if (c == character){
                countChars++;
            }
        }
        if (countDashes == 1 && countChars == 3){
            return true;
        }
        return false;
    }
}
