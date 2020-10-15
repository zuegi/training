package ch.wesr.connectfour.bitboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.swing.text.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Helper Tests")
public class HelperSpec {

    @Test
    void testMe() {
        final int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
        height[2]++;

        assertEquals(15, height[2]);
    }
}
