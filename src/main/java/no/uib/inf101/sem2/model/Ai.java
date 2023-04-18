package no.uib.inf101.sem2.model;

import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;

public class Ai {
    ConnectBoard board;
    ConnectModel model;
    Random rnd = new Random();

    public Ai(ConnectBoard board, ConnectModel model){
        this.model = model;
        this.board = board;
    }
    
    public void aiPlacePiece(){
        if (model.getTurn() == Turn.YELLOW){
            CellPosition pos = new CellPosition(0, findLucrativeSpot());
            while(board.get(pos) != '-'){
                pos = new CellPosition(0, rnd.nextInt(board.cols()));
            }
            board.set(pos, 'y');
            model.setNextTurn(new Piece('y', pos));
            
        }
    }

    public int findLucrativeSpot(){
        char[][] holes = board.getBoardAs2DArray();
        String holesOpt = board.prettyString();
        
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols() - 1; col++) {
                if (holes[row][col] == 'y' && holes[row][col+1] == '-'){
                    return col+1;
                }
                else if (holes[row][col] == 'y' && holes[row][col-1] == '-'){
                    return col-1;
                }
            }
        }


        return 0;
    }
}
