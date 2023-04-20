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

        if (col != -1){
            return col;
        }
        return rnd.nextInt(board.cols());



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
                return new AiPlacement(optimalCol, score);
        }
        return null;
    }

    private AiPlacement findColForRow(char[][] array) {
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

                if(board.threeOrFourInFour(fourCols, 'y', 3)){
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

                if(board.threeOrFourInFour(fourCols, 'r', 3)){
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

    
    private AiPlacement findColForUpDiag(char[][] array, char yOrR){
        for (int row = 3; row < board.rows(); row++) {
            for (int col = 0; col <= board.cols()-4; col++) {
                
                int colToReturn = -1;

                char a0 = array[row][col];
                char b1 = array[row - 1][col + 1];
                char c2 = array[row - 2][col + 2];
                char d3 = array[row - 3][col + 3];
                char e4 = '-';

                char[] sequence = {a0, b1, c2, d3};

                if (board.threeOrFourInFour(sequence, yOrR, 3)){

                    int sequenceIndex = String.valueOf(sequence).indexOf('-');
                    colToReturn = sequenceIndex + col;

                    e4 = array[row - sequenceIndex+1][colToReturn];

                    if (e4 != '-' && yOrR == 'y'){
                        return new AiPlacement(colToReturn, 5);
                    }
                    if (e4 != '-' && yOrR == 'r'){
                        return new AiPlacement(colToReturn, 4);
                    }
                }
            }
        }
        return null;
    }
    
    private AiPlacement findColForDownDiag(char[][] array, char yOrR){
        for (int row = 0; row <= board.rows()-4; row++) {
            for (int col = 0; col <= board.cols()-4; col++) {
                int colToReturn = -1;

                
                char a0 = array[row][col];
                char b1 = array[row + 1][col + 1];
                char c2 = array[row + 2][col + 2];
                char d3 = array[row + 3][col + 3];
                char e4 = '-';

                char[] sequence = {a0, b1, c2, d3};
                
                if (board.threeOrFourInFour(sequence, yOrR, 3)){
                    int sequenceIndex = String.valueOf(sequence).indexOf('-');
                    colToReturn = sequenceIndex + col;
                    e4 = array[row + sequenceIndex+1][colToReturn];

                    if (e4 != '-' && yOrR == 'y'){
                        return new AiPlacement(colToReturn, 5);
                    }
                    if (e4 != '-' && yOrR == 'r'){
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

}
