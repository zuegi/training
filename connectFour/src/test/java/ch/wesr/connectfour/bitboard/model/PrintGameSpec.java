package ch.wesr.connectfour.bitboard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintGameSpec {
    BitBoard bitBoard;
    DiscType[] discTypes = new DiscType[]{DiscType.O, DiscType.X};
    PrintGame printGame;

    @BeforeEach
    void init() {
        bitBoard = BitBoard.create();
        printGame = new PrintGame();
    }


    @Test
    void oneRowFull() {
        // given
        bitBoard.makeMove(DiscType.O, 0);
        bitBoard.makeMove(DiscType.X, 1);
        bitBoard.makeMove(DiscType.O, 2);
        bitBoard.makeMove(DiscType.X, 3);
        bitBoard.makeMove(DiscType.O, 4);
        bitBoard.makeMove(DiscType.X, 5);
        bitBoard.makeMove(DiscType.O, 6);
        // when
        String discTypeXString = printGame.convertBinaryToDiscTypeString(DiscType.X, bitBoard.getBitboardMap());
        String discTypeOString = printGame.convertBinaryToDiscTypeString(DiscType.O, bitBoard.getBitboardMap());
        String mergedDiscTypeString = printGame.merge(discTypeOString, discTypeXString);
        printGame.printBoard(bitBoard);
        // then
        assertEquals(DiscType.O.toChar(), mergedDiscTypeString.charAt(48));
        assertEquals(DiscType.X.toChar(), mergedDiscTypeString.charAt(41));
        assertEquals(DiscType.O.toChar(), mergedDiscTypeString.charAt(34));
        assertEquals(DiscType.X.toChar(), mergedDiscTypeString.charAt(27));
        assertEquals(DiscType.O.toChar(), mergedDiscTypeString.charAt(20));
        assertEquals(DiscType.X.toChar(), mergedDiscTypeString.charAt(13));
        assertEquals(DiscType.O.toChar(), mergedDiscTypeString.charAt(6));
    }

    @Test
    void mergedDiscTypes() {
        bitBoard.makeMove(DiscType.X, 0);
        String discTypeXString = printGame.convertBinaryToDiscTypeString(DiscType.X, bitBoard.getBitboardMap());
        assertEquals("000000000000000000000000000000000000000000000000X", discTypeXString);
        assertEquals(DiscType.X.toChar(), discTypeXString.charAt(48));

        bitBoard.makeMove(DiscType.O, 1);
        String discTypeOString = printGame.convertBinaryToDiscTypeString(DiscType.O, bitBoard.getBitboardMap());
        assertEquals(DiscType.O.toChar(), discTypeOString.charAt(41));

        bitBoard.makeMove(DiscType.X, 2);
        String discTypeX2String = printGame.convertBinaryToDiscTypeString(DiscType.X, bitBoard.getBitboardMap());
        assertEquals("0000000000000000000000000000000000X0000000000000X", discTypeX2String);

        // when
        String mergedDiscTypeString = printGame.merge(discTypeOString, discTypeX2String);

        // then
        assertEquals(DiscType.X.toChar(), mergedDiscTypeString.charAt(48));
        assertEquals(DiscType.O.toChar(), mergedDiscTypeString.charAt(41));
        assertEquals(DiscType.X.toChar(), mergedDiscTypeString.charAt(34));
    }

    @Test
    void discTypeStringXColumn0() {
        bitBoard.makeMove(DiscType.X, 0);
        String discTypeString = printGame.convertBinaryToDiscTypeString(DiscType.X, bitBoard.getBitboardMap());
        assertEquals("000000000000000000000000000000000000000000000000X", discTypeString);
    }

    @Test
    void discTypeStringXColumn1() {
        bitBoard.makeMove(DiscType.X, 1);
        String discTypeString = printGame.convertBinaryToDiscTypeString(DiscType.X, bitBoard.getBitboardMap());
        assertEquals("00000000000000000000000000000000000000000X0000000", discTypeString);
    }

    @Test
    void discTypeStringOColumn1() {
        bitBoard.makeMove(DiscType.O, 1);
        String discTypeString = printGame.convertBinaryToDiscTypeString(DiscType.O, bitBoard.getBitboardMap());
        assertEquals("00000000000000000000000000000000000000000O0000000", discTypeString);
    }

    @Test
    void discTypeIsSameOnPosition41() {
        bitBoard.makeMove(DiscType.O, 1);
        String discTypeString = printGame.convertBinaryToDiscTypeString(DiscType.O, bitBoard.getBitboardMap());
        assertTrue(String.valueOf(discTypeString.charAt(41)).equals(DiscType.O.name()));
    }

    @Test
    void discTypeIsSameOnPosition48() {
        bitBoard.makeMove(DiscType.X, 0);
        String discTypeString = printGame.convertBinaryToDiscTypeString(DiscType.X, bitBoard.getBitboardMap());
        assertTrue(String.valueOf(discTypeString.charAt(48)).equals(DiscType.X.name()));
    }
}
