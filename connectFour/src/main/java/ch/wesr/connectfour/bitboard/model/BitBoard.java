package ch.wesr.connectfour.bitboard.model;

import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import lombok.SneakyThrows;

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

    Map<Integer, Move> moves = new HashMap();

    public static BitBoard create() {
        return new BitBoard();
    }

    public void checkWinner(DiscType discType, long bitboard) {

        long diagonalTopLeft = bitboard & (bitboard >> 6) & (bitboard >> 12) & (bitboard >> 18);
        long diagonalDownLeft = bitboard & (bitboard >> 8) & (bitboard >> 16) & (bitboard >> 24);
        long horizontal = bitboard & (bitboard >> 7) & (bitboard >> 14) & (bitboard >> 21);
        long vertical = bitboard & (bitboard >> 1) & (bitboard >> 2) & (bitboard >> 3);

        if (diagonalTopLeft != 0) {
            throw new GameOverException("Player " + discType + " won diagonal top left to down right");
        } else if (diagonalDownLeft != 0) {
            throw new GameOverException("Player " + discType + " won diagonal down left to top right");
        } else if (horizontal != 0) {
            throw new GameOverException("Player " + discType + " won horizontal");
        } else if (vertical != 0) {
            throw new GameOverException("Player " + discType + " won vertical");
        }
    }

    public long makeMove(DiscType discType, int column) {
        Move potentialMove = new Move(discType, column);

        checkPossibleMove(potentialMove);
        long move = 1L << height[column]++;
        long bitboard = bitboardMap.get(discType);
        bitboard ^= move;

        checkWinner(discType, bitboard);

        bitboardMap.put(discType, bitboard);
        moves.put(counter++, potentialMove);
        return bitboardMap.get(discType);
    }

    @SneakyThrows
    private void checkPossibleMove(Move potentialMove) {
        int[] possibleMoves = listColumnsOfPossibleMoves();
        boolean contains = IntStream.of(possibleMoves).anyMatch(x -> x == potentialMove.getColumn());
        long count = possibleMoves.length;
        if (!isPossible(potentialMove, contains, count)) {
            throw new OutsideOfGameBoardException("DiscType [ " +potentialMove.getDiscType() + " ] plays outside of the board");
        }
    }

    private boolean isPossible(Move potentialMove, boolean contains, long count) {
        return contains && potentialMove.getColumn() >= 0 && potentialMove.getColumn() <= BitBoard.BOARD_WIDTH && count > 0;
    }

    @SneakyThrows
    public long undoMove(DiscType discType) {
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

    public int findBestColumn(DiscType discType) {
        // first discType is maximizer


        return 1;
    }

    public int[] listColumnsOfPossibleMoves() {
        List<Integer> moves = new ArrayList<>();
        long TOP = 0b1000000_1000000_1000000_1000000_1000000_1000000_1000000L;
        for(int col = 0; col <= 6; col++) {
            if ((TOP & (1L << height[col])) == 0) moves.add(col);
        }
        return moves.stream().mapToInt(i->i).toArray();
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
