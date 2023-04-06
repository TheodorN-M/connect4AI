package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Font;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(Character c) {
        Color color = switch(c) {
            case 'r' -> Color.RED;
            case 'y' -> Color.YELLOW;

            case '-' -> Color.WHITE;
            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
          };
          return color;
        
    }


    @Override
    public Color getBackgroundColor() {
        return Color.BLUE;
    }

    @Override
    public Font getFont() {
        return new Font("Comic Sans", Font.PLAIN, 50);
    }


    @Override
    public Color getFontColor() {
        return Color.WHITE;
    }

    @Override
    public Color getGameOverFadedColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Font getTurnFont(){
        return new Font("Comic Sans", Font.PLAIN, 20);
    }

}
