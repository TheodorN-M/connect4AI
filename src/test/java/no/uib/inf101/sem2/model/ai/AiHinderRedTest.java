package no.uib.inf101.sem2.model.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.model.ConnectModel;
import no.uib.inf101.sem2.model.GameState;

public class AiHinderRedTest {
    
    @Test
    public void findOptimalColForAiCol(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();

        board.set(new CellPosition(board.rows()-1, 3), 'r');
        board.set(new CellPosition(board.rows()-2, 3), 'r');
        board.set(new CellPosition(board.rows()-3, 3), 'r');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }

    @Test
    public void findOptimalColForAiRow(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();

        board.set(new CellPosition(board.rows()-1, 0), 'r');
        board.set(new CellPosition(board.rows()-1, 1), 'r');
        board.set(new CellPosition(board.rows()-1, 2), 'r');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());

        board.clear();

        board.set(new CellPosition(board.rows()-1, 0), 'r');
        board.set(new CellPosition(board.rows()-1, 1), 'r');
        board.set(new CellPosition(board.rows()-1, 3), 'r');

        expectedCol = 2;

        assertEquals(expectedCol, ai.findLucrativeSpot());

    }

    @Test
    public void findOptimalColForAiDiagUp(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        
        board.set(new CellPosition(3, 4), 'r');
        board.set(new CellPosition(4, 3), 'r');
        board.set(new CellPosition(5, 2), 'r');
        board.set(new CellPosition(3, 5), 'r');


        int expectedCol = 5;

        assertEquals(expectedCol, ai.findLucrativeSpot());

        board.clear();

        board.set(new CellPosition(3, 0), 'r');
        board.set(new CellPosition(2, 1), 'r');
        board.set(new CellPosition(1, 2), 'r');
        board.set(new CellPosition(1, 3), 'r');

        expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());


        board.clear();

        board.set(new CellPosition(4, 1), 'r');
        board.set(new CellPosition(2, 3), 'r');
        board.set(new CellPosition(1, 4), 'r');
        board.set(new CellPosition(4, 2), 'r');

        expectedCol = 2;

        assertEquals(expectedCol, ai.findLucrativeSpot());

    }

    @Test
    public void findOptimalColForAiDiagDown(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();

        board.set(new CellPosition(2, 2), 'r');
        board.set(new CellPosition(4, 4), 'r');
        board.set(new CellPosition(5, 5), 'r');
        board.set(new CellPosition(4, 3), 'r');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());

        board.clear();

        board.set(new CellPosition(0, 1), 'r');
        board.set(new CellPosition(1, 2), 'r');
        board.set(new CellPosition(2, 3), 'r');
        board.set(new CellPosition(4, 4), 'r');

        expectedCol = 4;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }

    
    

}
