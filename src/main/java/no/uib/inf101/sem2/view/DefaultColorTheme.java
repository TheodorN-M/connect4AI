package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Font;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(Character c) {
        Color color = switch(c) {
            case 'r' -> Color.RED;
            case 'g' -> Color.GREEN;
            case 'b' -> Color.BLUE;
            case 'y' -> Color.YELLOW;
            case 'p' -> Color.PINK;
            case 'o' -> Color.ORANGE;

            case 'T' -> new Color(213, 223, 229);
            case 'L' -> new Color(201, 177, 189);
            case 'J' -> new Color(180, 149, 148);
            case 'S' -> new Color(127, 145, 114);
            case 'Z' -> Color.PINK;
            case 'I' -> new Color(86, 117, 104);
            case 'O' -> new Color(203, 223, 189);

            case '-' -> getBackgroundColor();
            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
          };
          return color;
        
    }

    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLUE;
    
}

    @Override
    public Font getFont() {
        Font font = new Font("Comic Sans", Font.PLAIN, 50);
        return font;
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
    public Font getScoreFont(){
        Font font = new Font("Comic Sans", Font.PLAIN, 20);
        return font;
    }

}
