package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;
import no.uib.inf101.sem2.controller.ControllableModel;



public class ConnectModel implements ViewableConnectModel, ControllableModel {
    ConnectBoard board;

    GameState gameState = GameState.ACTIVE_GAME;
    Turn turn = Turn.RED;


    public ConnectModel(ConnectBoard board){
        this.board = board;

    }
    @Override
    public GridDimension getDimension(){
        return this.board;
    }
    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard(){
        return this.board;
    }


    
    /**
     * Checks if the piece's given position is valid
     * 
     * @param piece 
     * @return true or false
     */
    private boolean isLegalPos(Piece piece){

        CellPosition pos = piece.getPos();
        if (!(board.positionIsOnGrid(pos) && board.get(pos) == '-')){
            return false;
        }
        
        return true;
    }


    @Override
    public GameState getGameState() {
        return gameState;
    }


    @Override
    public void clockTick() {
        dropPieces();
    }

    public Turn getTurn(){
        return turn;
    }


    
    @Override
    public void placePiece(int col) {
        Piece piece = new Piece('y', new CellPosition(0, col));
        if (turn == Turn.RED){
            piece = new Piece('r', new CellPosition(0, col));
            nextTurn(piece);
        }
        
        if (isLegalPos(piece)){
            board.set(new CellPosition(0, col), piece.getColorCharacter());
            nextTurn(piece);
        }


    }
    private void nextTurn(Piece piece){
        if (piece.getColorCharacter() == 'r'){
            turn = Turn.YELLOW;
        }
        else{
            turn = Turn.RED;
        }

    }
    @Override
    public void dropPieces() {
        for (GridCell<Character> gridCell : board) {
            if (gridCell.value() != '-'){
                CellPosition newCP = new CellPosition(gridCell.pos().row() + 1, gridCell.pos().col());
                if (board.positionIsOnGrid(newCP) && board.get(newCP) == '-'){
                    board.set(gridCell.pos(), '-');
                    board.set(newCP, gridCell.value());
                }
            }
        }

    }
    @Override
    public int dropTimer() {
        return 800;
    }
    @Override
    public String getTurnAsString() {
        return "" + turn + "";
    }


}
