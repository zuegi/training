package ch.wesr.connectfour.bitboard.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscTypeTest {


    @Test
    void opponentOfX() {
        DiscType discType = DiscType.X;
        assertEquals(discType.opponent(), DiscType.O);
    }

    @Test
    void opponentOfO() {
        DiscType discType = DiscType.O;
        assertEquals(discType.opponent(), discType.X);
    }
}
