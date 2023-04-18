package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.ai.Ai;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;
import no.uib.inf101.sem2.controller.ControllableModel;

public class ConnectModel implements ViewableConnectModel, ControllableModel {
    private static Turn turn = Turn.RED;
    private final ConnectBoard board;
    private GameState gameState;;
    private String won = "";
    private final Ai ai;

    public ConnectModel(ConnectBoard board, GameState gamestate) {
        this.board = board;
        this.gameState = gamestate;
        if (gamestate == GameState.AI_ACTIVE){
            this.ai = new Ai(board, this);
        }
        else{
            this.ai = null;
        }

    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getHolesOnBoard() {
        return this.board;
    }

    /**
     * Checks if the piece's given position is valid
     * 
     * @param piece
     * @return true or false
     */
    private boolean isLegalPos(Piece piece) {

        CellPosition pos = piece.getPos();
        if ((!(board.positionIsOnGrid(pos)) || board.get(pos) != '-')) {
            return false;
        }

        return true;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    public void setGameOver() {
        gameState = GameState.GAME_OVER;
    }

    @Override
    public void clockTick() {
        Winner winner = new Winner(board);
        if (!board.dropPieces() && (gameState == GameState.ACTIVE_GAME || gameState == GameState.AI_ACTIVE)) {
            String s = winner.findWinner();
            if (s != null) {
                won = s;
                setGameOver();
            }
            if (gameState == GameState.AI_ACTIVE && turn == Turn.YELLOW){
                ai.aiPlacePiece();
            }
        }
    }

    @Override
    public Turn getTurn() {
        return turn;
    }

    @Override
    public String getWinnerString() {
        return won;
    }

    @Override
    public void placePiece(int col) {
        if (gameState == GameState.ACTIVE_GAME || gameState == GameState.AI_ACTIVE) {
            Piece piece = new Piece('y', new CellPosition(0, col));
            if (turn == Turn.RED) {
                piece = new Piece('r', new CellPosition(0, col));
                setNextTurn(piece);
            }

            if (isLegalPos(piece)) {
                board.set(new CellPosition(0, col), piece.getColorCharacter());
                setNextTurn(piece);
            }
        }
        

    }

    @Override
    public void setNextTurn(Piece currentPiece) {
        if (currentPiece.getColorCharacter() == 'r') {
            turn = Turn.YELLOW;
        } else {
            turn = Turn.RED;
        }

    }

    @Override
    public int dropTimer() {
        return 300;
    }

    @Override
    public String getTurnAsString() {
        return "" + turn;
    }

    @Override
    public void setTurn(Turn turn) {
        ConnectModel.turn = turn;
    }

}
