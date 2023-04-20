package no.uib.inf101.sem2.model.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ConnectBoard;
import no.uib.inf101.sem2.model.ConnectModel;
import no.uib.inf101.sem2.model.GameState;


public class AiWinTest {

    
    @Test
    public void findOptimalColForAiCol(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        int bottomRow = board.rows()-1;


        board.set(new CellPosition(bottomRow, 3), 'y');
        board.set(new CellPosition(bottomRow-1, 3), 'y');
        board.set(new CellPosition(bottomRow-2, 3), 'y');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }

    @Test
    public void findOptimalColForAiRow(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        int bottomRow = board.rows()-1;



        board.set(new CellPosition(bottomRow, 0), 'y');
        board.set(new CellPosition(bottomRow, 1), 'y');
        board.set(new CellPosition(bottomRow, 2), 'y');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());

        board.clear();

        board.set(new CellPosition(bottomRow, 0), 'y');
        board.set(new CellPosition(bottomRow, 1), 'y');
        board.set(new CellPosition(bottomRow, 3), 'y');

        expectedCol = 2;

        assertEquals(expectedCol, ai.findLucrativeSpot());

    }

    @Test
    public void advancedRowPlacementTest(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        int bottomRow = board.rows()-1;

        board.set(new CellPosition(bottomRow, 1), 'y');
        board.set(new CellPosition(bottomRow, 2), 'y');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }

    @Test
    public void advancedRowPlacementTest2(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        int bottomRow = board.rows()-1;

        board.set(new CellPosition(bottomRow-1, 2), 'y');
        board.set(new CellPosition(bottomRow-1, 3), 'y');

        board.set(new CellPosition(bottomRow, 1), 'r');
        board.set(new CellPosition(bottomRow, 4), 'r');
        board.set(new CellPosition(bottomRow, 5), 'r');


        int expectedCol = 4;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }
    
    @Test
    public void advancedRowPlacementTest3(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        int bottomRow = board.rows()-1;

        board.set(new CellPosition(bottomRow, 1), 'y');
        board.set(new CellPosition(bottomRow, 3), 'y');

        int expectedCol = 2;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }

    @Test
    public void findOptimalColForAiDiagUp(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();
        
        board.set(new CellPosition(3, 4), 'y');
        board.set(new CellPosition(4, 3), 'y');
        board.set(new CellPosition(5, 2), 'y');
        board.set(new CellPosition(3, 5), 'r');


        int expectedCol = 5;

        assertEquals(expectedCol, ai.findLucrativeSpot());

        board.clear();

        board.set(new CellPosition(3, 0), 'y');
        board.set(new CellPosition(2, 1), 'y');
        board.set(new CellPosition(1, 2), 'y');
        board.set(new CellPosition(1, 3), 'r');

        expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());


        board.clear();

        board.set(new CellPosition(5, 2), 'y');
        //                             4,      3
        board.set(new CellPosition(3, 4), 'y');
        board.set(new CellPosition(2, 5), 'y');
        board.set(new CellPosition(5, 3), 'r');

        expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());

    }

    @Test
    public void findOptimalColForAiDiagDown(){
        ConnectBoard board = new ConnectBoard(6, 7);
        ConnectModel model = new ConnectModel(board, GameState.AI_ACTIVE);
        Ai ai = model.getAi();

        board.set(new CellPosition(2, 2), 'y');
        board.set(new CellPosition(4, 4), 'y');
        board.set(new CellPosition(5, 5), 'y');
        board.set(new CellPosition(4, 3), 'r');

        int expectedCol = 3;

        assertEquals(expectedCol, ai.findLucrativeSpot());

        board.clear();

        board.set(new CellPosition(0, 1), 'y');
        board.set(new CellPosition(1, 2), 'y');
        board.set(new CellPosition(2, 3), 'y');
        board.set(new CellPosition(4, 4), 'r');

        expectedCol = 4;

        assertEquals(expectedCol, ai.findLucrativeSpot());
    }

    
    

}
