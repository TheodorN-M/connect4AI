package no.uib.inf101.sem2.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.view.ColorTheme;
import no.uib.inf101.sem2.view.DefaultColorTheme;

public class TestDefaultColorTheme {
    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(Color.BLUE, colors.getBackgroundColor());
        assertEquals(Color.WHITE, colors.getCellColor('-'));
        assertEquals(Color.RED, colors.getCellColor('r'));
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }

}
