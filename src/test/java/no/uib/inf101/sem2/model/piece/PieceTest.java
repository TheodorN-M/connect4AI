package no.uib.inf101.sem2.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class PieceTest {
    
    @Test
    public void pieceSanityTest(){
        Piece redPiece = new Piece('r', new CellPosition(0, 0));
        Piece redPiece2 = new Piece('r', new CellPosition(0, 0));
        Piece yellowPiece = new Piece('y', new CellPosition(0, 0));


        assertNotEquals(redPiece, redPiece2);
        assertNotEquals(redPiece, yellowPiece);
    }
    
    @Test
    public void testPiecePos(){
        Piece piece = new Piece('r', new CellPosition(0, 0));

        assertEquals(new CellPosition(0, 0), piece.getPos());
    }

    @Test
    public void testColorChar(){
        Piece piece = new Piece('r', new CellPosition(0, 0));

        assertEquals('r', piece.getColorCharacter());
    }

} 
