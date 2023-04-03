package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;
import no.uib.inf101.sem2.controller.ControllableModel;



public class ConnectModel implements ViewableConnectModel, ControllableModel {
    ConnectBoard board;
    String winner = "";
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
        if (!dropPieces() && gameState == GameState.ACTIVE_GAME){
            findWinner();
        }
    }
    
    public Turn getTurn(){
        return turn;
    }
    
    @Override
    public String getWinner(){
        return winner;
    }
    
    private void findWinner(){
        if (board.prettyString().contains("rrrr")){
            winner = "Red";
            gameState = GameState.GAME_OVER;
        }
        if (board.prettyString().contains("yyyy")){
            winner = "Yellow";
            gameState = GameState.GAME_OVER;
        }

        for (int col = 0; col < board.cols(); col++) {
            String colAString = String.valueOf(board.getCharArrayForCol(col));
            if (colAString.contains("rrrr")){
                winner = "Red";
                gameState = GameState.GAME_OVER;
            }
            if (colAString.contains("yyyy")){
                winner = "Yellow";
                gameState = GameState.GAME_OVER;
            }
        }
        
    }

    
    @Override
    public void placePiece(int col) {
        if (gameState == GameState.ACTIVE_GAME){
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
    public boolean dropPieces() {
        for (GridCell<Character> gridCell : board) {
            if (gridCell.value() != '-'){
                CellPosition newCP = new CellPosition(gridCell.pos().row() + 1, gridCell.pos().col());
                if (board.positionIsOnGrid(newCP) && board.get(newCP) == '-'){
                    board.set(gridCell.pos(), '-');
                    board.set(newCP, gridCell.value());
                    return true;
                }
            }
        }
        return false;

    }
    @Override
    public int dropTimer() {
        return 300;
    }
    @Override
    public String getTurnAsString() {
        return "" + turn;
    }


}