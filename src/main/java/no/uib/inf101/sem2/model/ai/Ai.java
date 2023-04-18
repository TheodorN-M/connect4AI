package no.uib.inf101.sem2.model.ai;

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
        char[][] holesFlipped = board.getFlippedBoardAs2DArray();

        AiPlacement hei = findColForCol();
        AiPlacement halla = findColForRow(holes);

        
        return bestAiMove(hei, halla);

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
                score = 4;
                intChanged = true;
            }
            if (intChanged) {
                if (board.get(new CellPosition(0, optimalCol)) == '-') {
                    return new AiPlacement(optimalCol, score);
                }
            }
        }
        return new AiPlacement(rnd.nextInt(board.cols()), score);
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
                score = 4;
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

    private int bestAiMove(AiPlacement one, AiPlacement two) {
        if (two == null){
            return one.col();
        } 

        int optimalCol = two.col();

        if (one.score() > two.score()) {
            optimalCol = one.col();
        }
        return optimalCol;
    }
}
