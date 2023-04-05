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

    private void setGameOver(){
        gameState = GameState.GAME_OVER;
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
    public String getWinnerString() {
        return winner;
    }
    @Override
    public void getWinner(char c){
        if (c == 'r'){
            winner = "Red";
        }
        else if (c == 'y'){
            winner = "Yellow";
        }
    }
    
    private void findWinner(){
        // Find horizontal winner with prettyString method
        if (board.prettyString().contains("rrrr")){
            winner = "Red";
            setGameOver();
        }
        if (board.prettyString().contains("yyyy")){
            winner = "Yellow";
            setGameOver();
        }

        // Find vertical winner with a character array as a string
        for (int col = 0; col < board.cols(); col++) {
            String colAsString = String.valueOf(board.getCharArrayForCol(col));
            if (colAsString.contains("rrrr")){
                winner = "Red";
                setGameOver();
            }
            if (colAsString.contains("yyyy")){
                winner = "Yellow";
                setGameOver();
            }
        }
        // Sjekke om noen har vunnet diagonalt
        char[][] DArray = board.getBoardAs2DArray();
        for (int col = 0; col < board.cols()-4; col++) {
            for (int row = 0; row < board.rows()-3; row++) {
                diagRightWinner(DArray, row, col);
                col += 4;
                diagLeftWinner(DArray, row, col);
                col -= 4;
            }
        }
        

    }
    private void diagRightWinner(char[][] board, int rowStart, int colStart){
        char a0 = board[rowStart][colStart];
        char b1 = board[rowStart +1][colStart +1];
        char c2 = board[rowStart +2][colStart +2];
        char d3 = board[rowStart +3][colStart +3];

        if (a0 == b1 && c2 == d3 && a0 == c2 && a0 != '-'){
            getWinner(a0);
            setGameOver();
        }
    }

    private void diagLeftWinner(char[][] board, int rowStart, int colStart){
        char a0 = board[rowStart][colStart];
        char b1 = board[rowStart +1][colStart -1];
        char c2 = board[rowStart +2][colStart -2];
        char d3 = board[rowStart +3][colStart -3];

        if (a0 == b1 && c2 == d3 && a0 == c2 && a0 != '-'){
            getWinner(a0);
            setGameOver();
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
