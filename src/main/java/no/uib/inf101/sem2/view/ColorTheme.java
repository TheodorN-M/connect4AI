package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Font;


public interface ColorTheme {
    /**
     * Converts a character value to a representing color
     * @param c character to convert to Color
     * @return a Color-object 
     */
    Color getCellColor(Character c);


    /**
     * 
     * Gets a color for the frame
     * @return a Color-object
     */
    Color getFrameColor();

    /**
     * Returns a Color object for the background
     * @return a Color-object
     * 
     */
    Color getBackgroundColor();

    /**
     * This method gets the font 
     * @return a Font-object with font style and size
     */
    Font getFont();

    /**
     * The method returns the given color wanted for the font
     * @return a Color-object for the font
     */
    Color getFontColor();

    /**
     * Gets a color to cover the screen when the player has lost and Gamestate is Game over.
     * @return a Color-object 
     */
    Color getGameOverFadedColor();

    /**
     * This method gets the font for the score at the top of the game window 
     * @return a Font-object with font style and size
     */
    Font getScoreFont();
}
