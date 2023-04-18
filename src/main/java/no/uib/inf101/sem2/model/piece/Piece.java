package no.uib.inf101.sem2.model.piece;

// import java.util.Arrays;
// import java.util.Objects;

import no.uib.inf101.sem2.grid.CellPosition;

public class Piece {
    private final char colorChar;
    private final CellPosition pos;

    public Piece(char colorChar, CellPosition pos) {
        this.colorChar = colorChar;
        this.pos = pos;
    }

    public char getColorCharacter() {
        return colorChar;
    }

    public CellPosition getPos() {
        return pos;
    }

    // @Override
    // public int hashCode(){
    //     return Objects.hash(this.colorChar, this.pos);
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (obj == this) {
    //       return true;
    //     }
    //     if (!(obj instanceof Piece)) {
    //       return false;
    //     }
    //     Piece other = (Piece) obj;
    //     return this.colorChar == other.colorChar &&  this.pos.equals(other.pos);
    // }

}
