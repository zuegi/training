package ch.wesr.connectfour.bitboard;

import ch.wesr.connectfour.bitboard.model.BitBoard;
import ch.wesr.connectfour.bitboard.model.DiscType;
import ch.wesr.connectfour.bitboard.model.PrintGame;
import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BitBoard Tests")
public class BitBoardSpec {

    BitBoard bitBoard;
    DiscType[] discTypes = new DiscType[]{DiscType.O, DiscType.X};
    PrintGame printGame;

    @BeforeEach
    void init() {
        bitBoard = BitBoard.create();
        printGame = new PrintGame();
    }


    @Test
    void findBestMove() {
        int values[] = {3, 5, 6, 9, 1, 2, 0, -1};
        int bestMove = bitBoard.findBestMove(0, 0,true, values,BitBoard.MIN, BitBoard.MAX);
        assertEquals(5, bestMove);
    }

    @Test
    void makeInvalidUndoMove() {
        long mostRecentlyMove = bitBoard.getMostRecentlyMove(DiscType.X);
        bitBoard.makeMove(DiscType.X, 2);
        bitBoard.makeMove(DiscType.O, 3);
        // TODO should be an error
        assertThrows(IllegalUndoMoveException.class, () -> {
            bitBoard.undoMove(DiscType.X, 2);
        });
    }

    @Test
    void makeValidUndoMove() {
        // given
        long mostRecentlyMove = bitBoard.getMostRecentlyMove(DiscType.X);
        bitBoard.makeMove(DiscType.X, 2);

        // when undoMove then
        assertEquals(mostRecentlyMove, bitBoard.undoMove(DiscType.X, 2), () -> "move should be " + mostRecentlyMove);
    }

    @Test
    void listPossibleMoves() {
        // given
        int[] expectedArray = new int[]{0, 1, 2, 4, 5, 6};
        // given gameboard
        // . . . X . . .
        // . . . O . . .
        // . . . X . . .
        // . . . O . . .
        // . . . X . . .
        // . . O X O . .
        // 0 1 2 3 4 5 6
        makeMove(DiscType.O ,2, 1);
        makeMove(DiscType.X,3, 2);
        makeMove(DiscType.O,4, 3);
        makeMove(DiscType.X, 3, 4);
        makeMove(DiscType.O,3, 5);
        makeMove(DiscType.X,3, 6);
        makeMove(DiscType.O, 3, 7);
        makeMove(DiscType.X, 3, 8);

        // when
        printGame.printBoard(bitBoard.getBitboardMap());
        // then
        assertTrue(Arrays.equals(expectedArray, bitBoard.listColumnsOfPossibleMoves()));


    }

    @DisplayName("Make alternating (O/X) moves for exactly one constellation")
    @Test
    void makeConstellationOXMoves() {
        // given
        // 5  . . . . . . .
        // 4  . . . . . . .
        // 3  . . . . . . .
        // 2  . . . O . . .
        // 1  . . . X X . .
        // 0  . . O X O . .
        //    0 1 2 3 4 5 6
        //                              col6    col5    col4    col3    col2    col1    co0
        String expectedByteStringX = "0000000 0000000 0000010 0000011 0000000 0000000 0000000";
        String expectedByteStringO = "0000000 0000000 0000001 0000100 0000001 0000000 0000000";
        // when
        makeMove(DiscType.O ,2, 1);
        makeMove(DiscType.X,3, 2);
        makeMove(DiscType.O,4, 3);
        makeMove(DiscType.X, 3, 4);
        long bitBoardLongO = makeMove(DiscType.O,3, 5);
        long bitBoardLongX = makeMove(DiscType.X,4, 6);
        printGame.printBoard(bitBoard.getBitboardMap());
        // then
        String convertByteStringO = printGame.convert(bitBoardLongO);
        assertEquals(expectedByteStringO, convertByteStringO, () -> "Converted byte String should be " +expectedByteStringO);

        String convertByteStringX = printGame.convert(bitBoardLongX);
        assertEquals(expectedByteStringX, convertByteStringX, () -> "Converted byte String should be " +expectedByteStringX);
    }

    // TODO rewrite these tests
    @DisplayName("Make alternating (O/X) moves only on column 0")
    @Test
    void makeAllMovesOnColumn0() {
        String finalExpectedBitboardLong_O = "0000000 0000000 0000000 0000000 0000000 0000000 0010101";
        String finalExpectedBitBoardLong_X = "0000000 0000000 0000000 0000000 0000000 0000000 0101010";
        long[] bitboardLongArray = new long[BitBoard.BOARD_HEIGHT];
        for (int height = 0; height < BitBoard.BOARD_HEIGHT; height++) {
            bitboardLongArray[height] = makeMove(discTypes[height & 1], 0, height +1);
        }
        assertEquals(finalExpectedBitBoardLong_X, printGame.convert(bitboardLongArray[BitBoard.BOARD_HEIGHT-1]), () -> "BinaryString should be " +finalExpectedBitBoardLong_X);
        assertEquals(finalExpectedBitboardLong_O, printGame.convert(bitboardLongArray[BitBoard.BOARD_HEIGHT-2]), () -> "BinaryString should be " +finalExpectedBitboardLong_O);
        printGame.printBoard(bitBoard.getBitboardMap());
    }


    @DisplayName("Make alternating (O/X) one move on each column")
    @Test
    void makeFirstMoveOnAllColumns() {
        String finalExpectedBitboardLong_O = "0000001 0000000 0000001 0000000 0000001 0000000 0000001";
        String finalExpectedBitBoardLong_X = "0000000 0000001 0000000 0000001 0000000 0000001 0000000";
        long[] bitboardLongArray = new long[BitBoard.BOARD_WIDTH];
        for (int width = 0; width < BitBoard.BOARD_WIDTH; width++) {
            bitboardLongArray[width] = makeMove(discTypes[width & 1],width, width +1);
        }

        assertEquals(finalExpectedBitboardLong_O, printGame.convert(bitboardLongArray[BitBoard.BOARD_WIDTH-1]), () -> "BinaryString should be " +finalExpectedBitboardLong_O);
        assertEquals(finalExpectedBitBoardLong_X, printGame.convert(bitboardLongArray[BitBoard.BOARD_WIDTH-2]), () -> "BinaryString should be " +finalExpectedBitBoardLong_X);
        printGame.printBoard(bitBoard.getBitboardMap());
    }

    private long makeMove(DiscType discType, int column, int moves) {
        long bitboardLong = bitBoard.makeMove(discType, column);
        String convertByteString = printGame.convert(bitboardLong);
        assertEquals( moves, bitBoard.getCounter(), () -> "Move should be " +moves );
        return bitboardLong;
    }

}
