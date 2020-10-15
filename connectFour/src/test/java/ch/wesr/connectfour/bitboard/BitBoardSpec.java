package ch.wesr.connectfour.bitboard;

import ch.wesr.connectfour.bitboard.model.BitBoard;
import ch.wesr.connectfour.bitboard.model.DiscType;
import ch.wesr.connectfour.bitboard.model.PrintGame;
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
    void printHelper() {
        BitBoardHelper.nextMoveCouldWinOnColumn4(bitBoard);
        printGame.printBoard(bitBoard);
    }




    @Test
    void findeBestColumnOn0() {

//        int bestColumn = bitBoard.findBestColumn(DiscType.X);
//        printGame.printBoard(bitBoard);||
//        assertEquals(0, bestColumn);
//        assertTrue(bitBoard.makeMove(DiscType.O, bestColumn));
//        printGame.printBoard(bitBoard);
    }


    @Test
    void makeOwinsLeftTopRightDownDiagonal() {
        // given 3 rows filled
        nextMoveOnColumn0CoulddWin(DiscType.O, DiscType.X);
        // when
        bitBoard.makeMove(DiscType.X, 0);
        printGame.printBoard(bitBoard);
        // when then
        assertTrue(bitBoard.isWinner(DiscType.X));
    }


    @Test
    void makeXwinsLeftDownTopRightDiagonal() {
        // given 3 rows filled
        nextMoveOnColumn0CoulddWin(DiscType.X, DiscType.O);
        // when
        bitBoard.makeMove(DiscType.X, 3);

        // then
        assertTrue(bitBoard.isWinner(DiscType.X));
    }

    @Test
    void makeOwinsVertical() {
        bitBoard.makeMove(DiscType.X, 0);
        bitBoard.makeMove(DiscType.O, 1);
        bitBoard.makeMove(DiscType.X, 2);
        bitBoard.makeMove(DiscType.O, 3);
        bitBoard.makeMove(DiscType.X, 3);
        bitBoard.makeMove(DiscType.O, 4);
        bitBoard.makeMove(DiscType.X, 1);
        bitBoard.makeMove(DiscType.O, 5);
        bitBoard.makeMove(DiscType.X, 2);
        // player O wins
        bitBoard.makeMove(DiscType.O, 6);
        printGame.printBoard(bitBoard);
        // then
        assertTrue(bitBoard.isWinner(DiscType.O));
    }

    @Test
    void makeXwinsHorizontal() {
        // given
        bitBoard.makeMove(DiscType.X, 0);
        bitBoard.makeMove(DiscType.O, 1);
        bitBoard.makeMove(DiscType.X, 0);
        bitBoard.makeMove(DiscType.O, 2);
        bitBoard.makeMove(DiscType.X, 0);
        bitBoard.makeMove(DiscType.O, 3);
        bitBoard.makeMove(DiscType.X, 0);
        // when then
        assertTrue(bitBoard.isWinner(DiscType.X));
    }

    @Test
    void makeInvalidUndoMove() {
        bitBoard.makeMove(DiscType.X, 2);
        bitBoard.makeMove(DiscType.O, 3);
        bitBoard.makeMove(DiscType.X, 3);
        bitBoard.undoMove(DiscType.O);
        bitBoard.undoMove(DiscType.X);
    }

    @Test
    void makeValidUndoMove() {
        bitBoard.makeMove(DiscType.X, 2);
        assertEquals(BitBoardHelper.COLUMN_2_POSITION_14, bitBoard.getBitboardMap().get(DiscType.X));
        bitBoard.makeMove(DiscType.O, 3);
        assertEquals(BitBoardHelper.COLUMN_3_POSITION_21, bitBoard.getBitboardMap().get(DiscType.O));
        // undod
        bitBoard.undoMove(DiscType.O);
        assertEquals(0L, bitBoard.getBitboardMap().get(DiscType.O));
        bitBoard.undoMove(DiscType.X);
        assertEquals(0L, bitBoard.getBitboardMap().get(DiscType.X));
    }

    @Test
    void makeMoveAndUndoIt() {
        long bitboardWithColumn2 = 16384;
        bitBoard.makeMove(DiscType.X, 2);
        assertEquals(BitBoardHelper.COLUMN_2_POSITION_14, bitBoard.getBitboardMap().get(DiscType.X));
        bitBoard.undoMove(DiscType.X);
        assertEquals(0L, bitBoard.getBitboardMap().get(DiscType.X));
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
        bitBoard.makeMove(DiscType.O, 2);
        bitBoard.makeMove(DiscType.X, 3);
        bitBoard.makeMove(DiscType.O, 4);
        bitBoard.makeMove(DiscType.X, 3);
        bitBoard.makeMove(DiscType.O, 3);
        bitBoard.makeMove(DiscType.X, 4);
        long bitBoardLongO = bitBoard.getMostRecentlyMove(DiscType.O);
        long bitBoardLongX = bitBoard.getMostRecentlyMove(DiscType.X);
        printGame.printBoard(bitBoard);
        // then
        assertEquals(expectedByteStringO, printGame.convert(bitBoardLongO), () -> "Converted byte String should be " + expectedByteStringO);
        assertEquals(expectedByteStringX, printGame.convert(bitBoardLongX), () -> "Converted byte String should be " + expectedByteStringX);
    }

    @Test
    void makeAllMovesOnColumn0AndOverboard() {
        int overboard = 0;
        for (int height = 0; height < BitBoard.BOARD_HEIGHT; height++) {
            bitBoard.makeMove(discTypes[height & 1], 0);
            overboard++;
        }
        // when overboard then
        bitBoard.makeMove(discTypes[overboard & 1], 0);
    }

    @DisplayName("Make alternating (O/X) moves only on column 0")
    @Test
    void makeAllMovesOnColumn0() {
        String finalExpectedBitboardLong_O = "0000000 0000000 0000000 0000000 0000000 0000000 0010101";
        String finalExpectedBitBoardLong_X = "0000000 0000000 0000000 0000000 0000000 0000000 0101010";
        for (int height = 0; height < BitBoard.BOARD_HEIGHT; height++) {
            bitBoard.makeMove(discTypes[height & 1], 0);

        }
        assertEquals(finalExpectedBitBoardLong_X, printGame.convert(bitBoard.getMostRecentlyMove(DiscType.X)), () -> "BinaryString should be " + finalExpectedBitBoardLong_X);
        assertEquals(finalExpectedBitboardLong_O, printGame.convert(bitBoard.getMostRecentlyMove(DiscType.O)), () -> "BinaryString should be " + finalExpectedBitboardLong_O);
        printGame.printBoard(bitBoard.getBitboardMap());
    }

    @Test
    void makeFirstMoveOnColumnsAndOverboard() {
        int overboard = 0;
        for (int width = 0; width < BitBoard.BOARD_WIDTH; width++) {
            bitBoard.makeMove(discTypes[width & 1], width);
            overboard++;
        }
        // when overboard then
        bitBoard.makeMove(discTypes[overboard & 1], overboard);

    }

    @DisplayName("Make alternating (O/X) one move on each column")
    @Test
    void makeFirstMoveOnAllColumns() {
        String finalExpectedBitboardLong_O = "0000001 0000000 0000001 0000000 0000001 0000000 0000001";
        String finalExpectedBitBoardLong_X = "0000000 0000001 0000000 0000001 0000000 0000001 0000000";

        for (int width = 0; width < BitBoard.BOARD_WIDTH; width++) {
            bitBoard.makeMove(discTypes[width & 1], width);
        }

        assertEquals(finalExpectedBitboardLong_O, printGame.convert(bitBoard.getMostRecentlyMove(DiscType.O)), () -> "BinaryString should be " + finalExpectedBitboardLong_O);
        assertEquals(finalExpectedBitBoardLong_X, printGame.convert(bitBoard.getMostRecentlyMove(DiscType.X)), () -> "BinaryString should be " + finalExpectedBitBoardLong_X);
        printGame.printBoard(bitBoard.getBitboardMap());
    }

    @Test
    void makeInvalidMove() {
        bitBoard.makeMove(DiscType.X, -1);
    }

    @Test
    void makeValidMove() {
        bitBoard.makeMove(DiscType.X, 0);
    }

    @Test
    void makeWinningMove() {
        nextMoveOnColumn0CoulddWin(DiscType.X, DiscType.O);
        bitBoard.makeMove(DiscType.O, 0);
        printGame.printBoard(bitBoard);
        assertTrue(bitBoard.isWinner(DiscType.O));
    }

    @Test
    void ListPossibleMovesButColumn2() {
        int[] expectedArray = new int[]{0, 1, 3, 4, 5, 6};
        makeOneColumnOccupied();
        assertTrue(Arrays.equals(expectedArray, bitBoard.listPossibleColumns()));
    }

    private void makeOneColumnOccupied() {
        bitBoard.makeMove(DiscType.X, 2);
        bitBoard.makeMove(DiscType.O, 2);
        bitBoard.makeMove(DiscType.X, 2);
        bitBoard.makeMove(DiscType.O, 2);
        bitBoard.makeMove(DiscType.X, 2);
        bitBoard.makeMove(DiscType.O, 2);
    }

    private void nextMoveOnColumn0CoulddWin(DiscType o, DiscType x) {
        bitBoard.makeMove(o, 0);
        bitBoard.makeMove(x, 1);
        bitBoard.makeMove(o, 2);
        bitBoard.makeMove(x, 3);
        bitBoard.makeMove(o, 4);
        // given second row
        bitBoard.makeMove(x, 0);
        bitBoard.makeMove(o, 1);
        bitBoard.makeMove(x, 2);
        bitBoard.makeMove(o, 3);
        bitBoard.makeMove(x, 4);
        // given third row
        bitBoard.makeMove(o, 0);
        bitBoard.makeMove(x, 1);
        bitBoard.makeMove(o, 2);
        bitBoard.makeMove(x, 3);
        bitBoard.makeMove(o, 4);
    }

    private void nextMoveOnColumn4CouldWin(DiscType o, DiscType x) {
        bitBoard.makeMove(x, 0);
        bitBoard.makeMove(o, 1);
        bitBoard.makeMove(x, 2);
        bitBoard.makeMove(o, 3);
        bitBoard.makeMove(x, 4);
        bitBoard.makeMove(o, 5);
        bitBoard.makeMove(x, 6);
        // given second row
        bitBoard.makeMove(o, 0);
        bitBoard.makeMove(x, 1);
        bitBoard.makeMove(o, 2);
        bitBoard.makeMove(x, 3);
        bitBoard.makeMove(o, 4);
        bitBoard.makeMove(x, 5);
        bitBoard.makeMove(o, 6);
        // given third row
        bitBoard.makeMove(x, 6);
        bitBoard.makeMove(o, 5);
        bitBoard.makeMove(x, 4);
        bitBoard.makeMove(o, 3);
        bitBoard.makeMove(x, 2);
        bitBoard.makeMove(o, 1);
        bitBoard.makeMove(x, 0);
        // and now
        bitBoard.makeMove(o, 1);
        bitBoard.makeMove(x, 0);
        bitBoard.makeMove(o, 3);
        assertFalse(bitBoard.isWinner(o));
        bitBoard.makeMove(x, 2);
        assertFalse(bitBoard.isWinner(x));
        bitBoard.makeMove(o, 5);
        assertFalse(bitBoard.isWinner(o));
        bitBoard.makeMove(x, 4);
        assertFalse(bitBoard.isWinner(x));

        bitBoard.makeMove(o, 0);
        assertFalse(bitBoard.isWinner(o));

        bitBoard.makeMove(x, 6);
        assertFalse(bitBoard.isWinner(x));

        bitBoard.makeMove(x, 1);
        assertFalse(bitBoard.isWinner(x));

        bitBoard.makeMove(o, 2);
        assertFalse(bitBoard.isWinner(o));
        bitBoard.makeMove(x, 3);
        assertFalse(bitBoard.isWinner(x));

        bitBoard.makeMove(o, 4);
        assertFalse(bitBoard.isWinner(o));

        bitBoard.makeMove(x, 5);
        assertFalse(bitBoard.isWinner(x));

        bitBoard.makeMove(o, 6);
        assertFalse(bitBoard.isWinner(o)); // row 4 finished
        // row 5
        bitBoard.makeMove(x, 2);
        assertFalse(bitBoard.isWinner(x));
        bitBoard.makeMove(o, 6);
        assertFalse(bitBoard.isWinner(o));
        bitBoard.makeMove(x, 0);
        assertFalse(bitBoard.isWinner(x));
    }

}
