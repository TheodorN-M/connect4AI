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

    private int findLucrativeSpot() {
        char[][] holes = board.getBoardAs2DArray();
        // char[][] holesFlipped = board.getFlippedBoardAs2DArray();

        AiPlacement vertical = findColForCol();
        AiPlacement horizontal = findColForRow(holes);

        AiPlacement hinderRedH = hinderRedHorizontally(holes);
        AiPlacement hinderRedV = hinderRedVertically(holes);

        
        int col = bestAiMove(vertical, horizontal, hinderRedH, hinderRedV);
        if (col != -1){
            return col;
        }
        return rnd.nextInt(board.cols());


    }

    private AiPlacement findColForCol() {
        int optimalCol = -1;
        int score = 1;

        for (int col = 0; col < board.cols(); col++) {
            String currentCol = String.valueOf(board.getCharArrayForCol(col));
            boolean intChanged = false;

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
            if (currentCol.contains("-yyy")) {
                optimalCol = col;
                score = 5;
                intChanged = true;
            }
            if (intChanged) {
                if (board.get(new CellPosition(0, optimalCol)) == '-') {
                    return new AiPlacement(optimalCol, score);
                }
            }
        }
        return null;
    }

    private AiPlacement findColForRow(char[][] array) {
        int optimalCol = -1;
        int score = 1;
        for (int row = 0; row < board.rows(); row++) {
            String currentRowString = String.valueOf(array[row]);
            if (currentRowString.contains("-y")) {
                score = 2;
            }
            if (currentRowString.contains("-yy")) {
                score = 3;
            }
            if (currentRowString.contains("-yyy")) {
                score = 5;
            }

            for (int col = 0; col < board.cols() - 1; col++) {
                if (col != 0 && array[row][col] == 'y' && array[row][col - 1] == '-') {
                    return new AiPlacement(col-1, score);
                } else if (array[row][col] == 'y' && array[row][col + 1] == '-') {
                    return new AiPlacement(col+1, score);
                    
                }
            }
        }
        return null;
    }

    private AiPlacement hinderRedHorizontally(char[][] array){
        for (int row = 0; row < board.rows(); row++) {
          char[] currentRow = array[row];

            for (int col = 0; col < board.cols() - 3; col++) {
                int countDashes = 0;
                int countReds = 0;
                char[] fourCols = {currentRow[col], currentRow[col+1], currentRow[col+2], currentRow[col+3]};
                System.out.println(String.valueOf(fourCols));
                for (char c : fourCols) {
                    if(c == '-'){
                        countDashes++;
                    }
                    if (c == 'r'){
                        countReds++;
                    }
                }
                if(countDashes == 1 && countReds == 3){
                    return new AiPlacement(String.valueOf(fourCols).indexOf('-')+ col, 4);
                }

                // if (col != 0 && currentRow[col] == 'r' && currentRow[col - 1] == '-' && 
                //     currentRow[col + 1] == 'r' && currentRow[col + 2] == 'r') {

                //     return new AiPlacement(col-1, 3);

                // } else if (col != board.cols()-4 && currentRow[col] == 'r' && currentRow[col + 1] == 'r' && 
                //            currentRow[col + 2] == 'r' && currentRow[col + 3] == '-') {

                //     return new AiPlacement(col+3, 4);
                    
                // }
            }
        }
        return null;
    }

    private AiPlacement hinderRedVertically(char[][] array){

        for (int col = 0; col < board.cols(); col++) {
            String currentCol = String.valueOf(board.getCharArrayForCol(col));

            if (currentCol.contains("-rrr")) {
                System.out.println(currentCol);
                return new AiPlacement(col, 4);

            }
        }
        return null;
    }

    private int bestAiMove(AiPlacement one, AiPlacement two, AiPlacement three, AiPlacement four) {
        List<AiPlacement> options = new ArrayList<>();
        if (one != null){
            options.add(one);
        }
        if (two != null){
            options.add(two);
        }
        if (three != null){
            options.add(three);
        }
        if(four != null){
            options.add(four);
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
