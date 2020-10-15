package ch.wesr.connectfour.bitboard.model;

import java.util.*;
import java.util.stream.IntStream;

public class BitBoard {
    public static int MAX_SLOTS = 42;
    public static int BOARD_WIDTH = 7;
    public static int BOARD_HEIGHT = 6;
    private final int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
    private final int[] moves = new int[MAX_SLOTS];
    private int counter;
    Map<DiscType, Long> bitboardMap = new HashMap<>(
            Map.of(DiscType.O, 0L, DiscType.X, 0L));

    PrintGame printGame = new PrintGame();

    public static BitBoard create() {
        return new BitBoard();
    }

    public boolean isWinner(DiscType discType) {
        Long bitboard = this.bitboardMap.get(discType);
        long diagonalTopLeft = bitboard & (bitboard >> 6) & (bitboard >> 12) & (bitboard >> 18);
        long diagonalDownLeft = bitboard & (bitboard >> 8) & (bitboard >> 16) & (bitboard >> 24);
        long horizontal = bitboard & (bitboard >> 7) & (bitboard >> 14) & (bitboard >> 21);
        long vertical = bitboard & (bitboard >> 1) & (bitboard >> 2) & (bitboard >> 3);

        if (diagonalTopLeft != 0) {
            return true;
        } else if (diagonalDownLeft != 0) {
            return true;
        } else if (horizontal != 0) {
            return true;
        } else if (vertical != 0) {
            return true;
        }

        return false;
    }


    public void makeMove(DiscType discType, int column) {
        if (isPossibleColumn(column)) {
            long move = 1L << height[column]++;
            Long bitboardOfDiscType = bitboardMap.get(discType);
            bitboardOfDiscType ^= move;
            bitboardMap.put(discType, bitboardOfDiscType);
            moves[counter++] = column;
        }

    }

    public void undoMove(DiscType discType) {
        int column = moves[--counter];
        long move = 1L << --height[column];
        Long bitboardOfDiscType = bitboardMap.get(discType);
        bitboardOfDiscType ^= move;
        bitboardMap.put(discType, bitboardOfDiscType);
    }

    public boolean isPossibleColumn(int column) {
        int[] possibleMoves = listPossibleColumns();
        boolean contains = IntStream.of(possibleMoves).anyMatch(x -> x == column);
        long count = possibleMoves.length;
        return isPossible(column, contains, count);
    }

    private boolean isPossible(int column, boolean contains, long count) {
        return contains && column >= 0 && column <= BitBoard.BOARD_WIDTH && count > 0;
    }

    public int findBestColumn(DiscType discType) {

        int bestScore = Integer.MIN_VALUE;
        int bestColumn = 0;

        int score = minimax(MAX_SLOTS, discType, true);
        if (score > bestScore) {
            bestScore = score;
        }
        return bestColumn;
    }

    private int minimax(int depth, DiscType discType, boolean maximizing) {
        // Terminating condition
        if (depth == 0) {
            return 0;
        }

        if (maximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int potentialColumn : this.listPossibleColumns()) {
                int score;
                if (isWinner(discType)) {
                    score = -1;
                } else if (isWinner(discType.opponent())) {
                    score = 1;
                } else {
                    this.makeMove(discType, potentialColumn);
                    score = minimax(depth - 1, discType.opponent(), false);
                }
                this.undoMove(discType);

                bestScore = Math.max(score, bestScore);
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int potentialColumn : this.listPossibleColumns()) {
                int score;
                if (isWinner(discType)) {
                    score = 1;
                } else if (isWinner(discType.opponent())) {
                    score = -1;
                } else {
                    this.makeMove(discType, potentialColumn);
                    score = minimax(depth - 1, discType.opponent(), true);
                }
                this.undoMove(discType);

                bestScore = Math.min(score, bestScore);

            }
            return bestScore;
        }

    }

    public int[] listPossibleColumns() {
        List<Integer> moves = new ArrayList<>();
        long TOP = 0b1000000_1000000_1000000_1000000_1000000_1000000_1000000L;
        for (int col = 0; col <= BOARD_WIDTH - 1; col++) {
            if ((TOP & (1L << height[col])) == 0) moves.add(col);
        }
        return moves.stream().mapToInt(i -> i).toArray();
    }


    public long getMostRecentlyMove(DiscType discType) {
        return bitboardMap.get(discType);
    }

    public Map<DiscType, Long> getBitboardMap() {
        return this.bitboardMap;
    }

}
