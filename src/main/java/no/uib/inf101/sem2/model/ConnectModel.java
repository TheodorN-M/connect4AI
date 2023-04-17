package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.piece.Piece;
import no.uib.inf101.sem2.model.piece.Turn;
import no.uib.inf101.sem2.controller.ControllableModel;

public class ConnectModel implements ViewableConnectModel, ControllableModel {
    private ConnectBoard board;
    private static GameState gameState = GameState.ACTIVE_GAME;
    private static Turn turn = Turn.RED;
    private static String won = "";

    public ConnectModel(ConnectBoard board) {
        this.board = board;
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
        if (!board.dropPieces() && gameState == GameState.ACTIVE_GAME) {
            String s = winner.findWinner();
            if (s != null) {
                won = s;
                setGameOver();
            }
        }
    }

    public Turn getTurn() {
        return turn;
    }

    @Override
    public String getWinnerString() {
        return won;
    }

    @Override
    public void placePiece(int col) {
        if (gameState == GameState.ACTIVE_GAME) {
            Piece piece = new Piece('y', new CellPosition(0, col));
            if (turn == Turn.RED) {
                piece = new Piece('r', new CellPosition(0, col));
                nextTurn(piece);
            }

            if (isLegalPos(piece)) {
                board.set(new CellPosition(0, col), piece.getColorCharacter());
                nextTurn(piece);
            }
        }

    }

    private void nextTurn(Piece piece) {
        if (piece.getColorCharacter() == 'r') {
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
