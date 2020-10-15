package ch.wesr.connectfour.bitboard;

import ch.wesr.connectfour.bitboard.model.DiscType;
import ch.wesr.connectfour.bitboard.model.PrintGame;
import ch.wesr.connectfour.mySolution.model.Disc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitwiseOperationSpec {

    PrintGame printGame = new PrintGame();
    int[] height;
    int counter;
    long[] bitboard;
    private int[] moves;
    Map<DiscType, Long> bitboardMap;

    @BeforeEach
    void init() {
        bitboard = new long[2];
        bitboardMap = new HashMap<>(Map.of(DiscType.O, 0L, DiscType.X, 0L));
        moves = new int[48];
        height = new int[]{0, 7, 14, 21, 28, 35, 42};
        counter = 0;
    }


    @Test
    void counterAmpersand() {
        assertEquals(0, 0 & 1);
        assertEquals(1, 1 & 1);
        assertEquals(0, 2 & 1);
        assertEquals(1, 3 & 1);
    }

    @Test
    void makeMoveUndoMoveWithMap() {
        makeMove(DiscType.X, 2);
        System.out.println(printGame.convert(bitboardMap.get(DiscType.X)));
        assertEquals(16384, bitboardMap.get(DiscType.X));
        undoMove(DiscType.X);
        System.out.println(printGame.convert(bitboardMap.get(DiscType.X)));
        assertEquals(0, bitboardMap.get(DiscType.X));
    }

    @Test
    void makeMoveMakeMoveUndoMoveWithMap() {
        long bitboardX = 16384;
        long bitboardO = 2097152;
        makeMove(DiscType.X,2);
        makeMove(DiscType.O,3);
        assertEquals(bitboardX, bitboardMap.get(DiscType.X));
        assertEquals(bitboardO, bitboardMap.get(DiscType.O));

        undoMove(DiscType.O);
        assertEquals(0L, bitboardMap.get(DiscType.O));
        assertEquals(bitboardX, bitboardMap.get(DiscType.X));

        undoMove(DiscType.X);
        assertEquals(0L, bitboardMap.get(DiscType.X));
        assertEquals(0L, bitboardMap.get(DiscType.O));

    }


    @Test
    void makeAndUndoMoveWithLoop() {
        for (int column = 0; column <= 6; column++) {
            if (column % 2 == 0) {
                makeMove(DiscType.X, column);
            } else {
                makeMove(DiscType.O, column);
            }
        }

        printGame.printBoard(bitboardMap);

        for (int column = 6; column >= 0; column--) {
            if (column % 2 == 0) {
                undoMove(DiscType.X);
            } else {
                undoMove(DiscType.O);
            }
        }
        printGame.printBoard(bitboardMap);
        assertEquals(0L, bitboardMap.get(DiscType.X));
        assertEquals(0L, bitboardMap.get(DiscType.O));
    }

    private void makeMove(DiscType discType, int column) {
        long move = 1L << height[column]++;
        Long bitboardOfDiscType = bitboardMap.get(discType);
        bitboardOfDiscType ^= move;
        bitboardMap.put(discType, bitboardOfDiscType);
        moves[counter++] = column;
    }

    private void undoMove(DiscType discType) {
        int column = moves[--counter];
        long move = 1L << --height[column];
        Long bitboardOfDiscType = bitboardMap.get(discType);
        bitboardOfDiscType ^= move;
        bitboardMap.put(discType, bitboardOfDiscType);
    }

    @Test
    void makeMoveUndoMove() {
        makeMove(2);
        System.out.println(printGame.convert(bitboard[0]));
        assertEquals(16384, bitboard[0]);
        undoMove();
        System.out.println(printGame.convert(bitboard[0]));
        assertEquals(0, bitboard[0]);
    }

    @Test
    void makeMoveMakeMoveUndoMove() {
        long bitboard0 = 16384;
        long bitboard1 = 2097152;
        makeMove(2);
        System.out.println(bitboard[0] +", " +printGame.convert(bitboard[0]));
        makeMove(3);
        assertEquals(bitboard0, bitboard[0]);
        System.out.println(bitboard[1] +", " +printGame.convert(bitboard[1]));
        assertEquals(bitboard1, bitboard[1]);
        undoMove();
        assertEquals(bitboard0, bitboard[0]);
        assertEquals(0L, bitboard[1]);
        System.out.println("------------");
        System.out.println(printGame.convert(bitboard[0]));
        System.out.println(printGame.convert(bitboard[1]));
        undoMove();
        assertEquals(0L, bitboard[0]);
        assertEquals(0L, bitboard[1]);
        System.out.println("------------");
        System.out.println(printGame.convert(bitboard[0]));
        System.out.println(printGame.convert(bitboard[1]));

    }

    private void makeMove(int column) {
        long move = 1L << height[column]++;
        bitboard[counter &1] ^= move;
        moves[counter++] = column;
    }

    private void undoMove() {
        int column = moves[--counter];
        long move = 1L << --height[column];
        bitboard[counter &1] ^= move;
    }

    @Test
    void test() {
        long bitBoard = 0L;
        long position = 14L; // height[2]
        long newPosition = 1L << position;
        System.out.println(printGame.convert(bitBoard));
        System.out.println(printGame.convert(newPosition));
        bitBoard ^= newPosition;
        System.out.println(printGame.convert(bitBoard));
        long nextPosition = 1L << bitBoard;
        System.out.println(printGame.convert(newPosition));
        bitBoard ^= newPosition;
        System.out.println(printGame.convert(bitBoard));
    }


}
