package ch.wesr.connectfour.bitboard.model;

import java.util.*;
import java.util.stream.IntStream;

public class BitBoard {

    public static final int MIN = -1000;
    public static final int MAX = 1000;
    public static int BOARD_WIDTH = 7;
    public static int BOARD_HEIGHT = 6;

    private final int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
    private int counter = 0;
    Map<DiscType, Long> bitboardMap = new HashMap<>(
            Map.of(DiscType.O, 0L, DiscType.X, 0L));

    Map<Integer, DiscType> moves = new HashMap<>();

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

    public boolean makeMove(DiscType discType, int column) {
        if (isPossibleColumn(column)) {
            long move = 1L << height[column]++;
            long bitboard = bitboardMap.get(discType);
            bitboard ^= move;

            bitboardMap.put(discType, bitboard);
            moves.put(column, discType);
            return true;
        }
        return false;
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


    public boolean undoMove(DiscType discType, int column) {
        if (isPossibleColumn(column)) {
            long moveLong = 1L << --height[column];
            long aLong = bitboardMap.get(discType);
            aLong ^= moveLong;
            bitboardMap.put(discType, aLong);
            return true;
        }
        return false;
    }

    public int findBestColumn(DiscType discType) {

        int bestScore = Integer.MIN_VALUE;
        int bestColumn = 0;
        for (int potentialColumn : this.listPossibleColumns()) {

            this.makeMove(discType, potentialColumn);
            int score = minimax(discType.opponent(), 0);
            this.undoMove(discType, potentialColumn);

            if (score > bestScore) {
                bestScore = score;
                bestColumn = potentialColumn;
            }
        }
        return bestColumn;
    }

    private int minimax(DiscType discType, int depth) {
        // Terminating condition
        if (isWinner(discType)) {
            int minimax = 0;
            if(discType.equals(DiscType.X))
                minimax = -1;
            if (discType.equals(DiscType.O))
                minimax = 1;
            return minimax;
        }
        boolean maximizing = discType.equals(DiscType.X);

        if (maximizing) {

            int bestScore = Integer.MIN_VALUE;
            for (int potentialColumn : this.listPossibleColumns()) {

                this.makeMove(discType, potentialColumn);
                int score = minimax(discType.opponent(), depth +1);
                this.undoMove(discType, potentialColumn);

                bestScore = Math.max(score, bestScore);

            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int potentialColumn : this.listPossibleColumns()) {

                this.makeMove(discType, potentialColumn);

                int score = minimax(discType.opponent(), depth +1);
                this.undoMove(discType, potentialColumn);

                bestScore = Math.min(score, bestScore);

            }
            return bestScore;
        }

    }

    public int[] listPossibleColumns() {
        List<Integer> moves = new ArrayList<>();
        long TOP = 0b1000000_1000000_1000000_1000000_1000000_1000000_1000000L;
        for (int col = 0; col <= 6; col++) {
            if ((TOP & (1L << height[col])) == 0) moves.add(col);
        }
        return moves.stream().mapToInt(i -> i).toArray();
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
