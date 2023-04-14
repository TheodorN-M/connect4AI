package no.uib.inf101.sem2.model.piece;

import no.uib.inf101.sem2.grid.CellPosition;

public class Piece {
    private final char colorChar;
    private final CellPosition pos;

    public Piece(char colorChar, CellPosition pos){
        this.colorChar = colorChar;
        this.pos = pos;
    }

    public char getColorCharacter(){
        return colorChar;
    }

    public CellPosition getPos(){
        return pos;
    }
}
