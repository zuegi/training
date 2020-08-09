package ch.wesr.connectfour.bitboard.model;

import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import lombok.SneakyThrows;

import java.util.*;

public class BitBoard {

    public static final int MIN = -1000;
    public static final int MAX = 1000;
    public static int BOARD_WIDTH = 7;
    public static int BOARD_HEIGHT = 6;

    private final int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
    private int counter = 0;
    Map<DiscType, Long> bitboardMap = new HashMap<DiscType, Long>(
            Map.of(DiscType.O, 0L, DiscType.X, 0L));

    Map<Integer, Move> moves = new HashMap();

    public static BitBoard create() {
        return new BitBoard();
    }

    public long makeMove(DiscType discType, int column) {
        moves.put(counter++, new Move(discType, column));
        long move = 1L << height[column]++;
        long aLong = bitboardMap.get(discType);
        aLong ^= move;
        bitboardMap.put(discType, aLong);
        return bitboardMap.get(discType);
    }

    @SneakyThrows
    public long undoMove(DiscType discType, int column) {
        Move move = moves.get(--counter);
        if (!move.getDiscType().equals(discType)) {
            throw new IllegalUndoMoveException("halleljua");
        }
        int col = move.getColumn();
        long moveLong = 1L << --height[col];
        long aLong = bitboardMap.get(discType);
        aLong ^= moveLong;
        bitboardMap.put(discType, aLong);
        return bitboardMap.get(discType);
    }

    public List<Integer> listColumnsOfPossibleMoves() {
        List<Integer> moves = new ArrayList<>();
        long TOP = 0b1000000_1000000_1000000_1000000_1000000_1000000_1000000L;
        for(int col = 0; col <= 6; col++) {
            if ((TOP & (1L << height[col])) == 0) moves.add(col);
        }
        return moves;
    }


    public int findBestMove(int depth, boolean maximizer) {

//        leafNode hat etwas mit depth zu tun - weiss nur noch nicht was
//        und BOARD_HEIGHT
//        if(leafNode is reached)
//            return wasWeissich;

        int bestMove;
        int value = 0;
        if (maximizer) {
            bestMove = MIN;
            for (Integer possibleCurrentMove : this.listColumnsOfPossibleMoves()) {
                value = findBestMove(depth + 1, false);
                bestMove = Math.max(bestMove, value);
            }

        } else {
            bestMove = MAX;
            for (Integer possibleCurrentMove : this.listColumnsOfPossibleMoves()) {
                value = findBestMove(depth + 1, false);
                bestMove = Math.max(bestMove, value);
            }
        }

        return bestMove;
    }

    public int getCounter() {
        return counter;
    }

    public long getMostRecentlyMove(DiscType discType) {
        return bitboardMap.get(discType);
    }

    public Map<DiscType, Long> getBitboardMap() {
        return this.bitboardMap;
    }

}
