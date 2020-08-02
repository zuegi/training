package ch.wesr.connectfour.bitboard;

import ch.wesr.connectfour.bitboard.model.BitBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BitBoardSpec {

    BitBoard bitBoard;

    @BeforeEach
    void init() {
        bitBoard = BitBoard.create();
    }

    @Test
    void makeMoves() {
//        String expectedByteStringX = "0000000|0000000|0000001|0000100|0000001|0000000|0000000";
        String expectedByteStringO = "0000000|0000000|0000010|0000011|0000000|0000000|0000000";
        makeMove(1, 1); // O
        makeMove(3, 2); // X
        makeMove(4, 3); // O
        makeMove(3, 4); // X
        long bitboardLong = makeMove(4, 5); // O
        String convertByteString = bitBoard.convert(bitboardLong);
        assertEquals(expectedByteStringO, convertByteString, () -> "Converted byte String should be " +expectedByteStringO);
    }

    @Test
    void makeAllMovesOnColumn0() {
        for (int width = 0; width <= BitBoard.BOARD_WIDTH; width++) {
            long bitboardLong = makeMove(0, width +1);
            String convertByteString = bitBoard.convert(bitboardLong);
            System.out.println("Current bitBoard for column ["+width +"]" +convertByteString);
        }
    }


    @Test
    void makeFirstMoveOnAllColumns() {
        for (int column = 0; column <= BitBoard.BOARD_HEIGHT; column++) {
            long bitboardLong = makeMove(column, column +1);
            String convertByteString = bitBoard.convert(bitboardLong);
            System.out.println("Current bitBoard for column ["+column +"]" +convertByteString);
        }
    }

    private long makeMove(int column, int moves) {
        long bitboardLong = bitBoard.makeMove(column);
        String convertByteString = bitBoard.convert(bitboardLong);
        assertEquals( moves, bitBoard.getCounter(), () -> "Move should be " +moves );
        assertEquals(convertByteString, bitBoard.convert(), () -> "BinaryString should be " +convertByteString);
        return bitboardLong;
    }

}
