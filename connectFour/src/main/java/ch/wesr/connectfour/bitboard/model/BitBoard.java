package ch.wesr.connectfour.bitboard.model;

import java.util.*;
import java.util.stream.Collectors;

public class BitBoard {

    public static final String SEPARATOR = " ";
    public static int BOARD_WIDTH = 7;
    public static int BOARD_HEIGHT = 6;

    private final int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
    private int counter = 0;
    Map<DiscType, Long> bitboardMap = new HashMap<DiscType, Long>(
            Map.of(DiscType.O, 0L, DiscType.X, 0L));

    Map<Integer, Integer> moves = new HashMap();

    public static BitBoard create() {
        return new BitBoard();
    }

    public long makeMove(DiscType discType, int column) {
        // history of the moves - used in undo
        moves.put(counter++, column);
        long move = 1L << height[column]++;
        long aLong = bitboardMap.get(discType);
        aLong ^= move;
        bitboardMap.put(discType, aLong);
        return bitboardMap.get(discType);
    }

    public long undoMove(DiscType discType, int column) {
        // TODO discType is not considered
        int col = moves.get(--counter);
        long move = 1L << --height[col];
        long aLong = bitboardMap.get(discType);
        aLong ^= move;
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


    public void printBoard() {
        String bitBoardBinaryStringO = this.convert(bitboardMap.get(DiscType.O));
        String[] arrayOfColumnsO = bitBoardBinaryStringO.split(SEPARATOR);

        String bitBoardBinaryStringX = this.convert(bitboardMap.get(DiscType.X));
        String[] arrayOfColumnsX = bitBoardBinaryStringX.split(SEPARATOR);

        for (int height = BOARD_HEIGHT - 1; height >= 0; height--) {
            // left side numbering
            System.out.print(height + "  ");

            for (int width = BOARD_WIDTH - 1; width >= 0; width--) {
                String[] splittedO = arrayOfColumnsO[width].split("");
                String[] splittedX = arrayOfColumnsX[width].split("");

                if (splittedO[BOARD_HEIGHT - height].equals(splittedX[BOARD_HEIGHT - height])) {
                    System.out.print(". ");
                } else if (splittedO[BOARD_HEIGHT - height].equals("1") && splittedX[height].equals("0")) {
                    System.out.print(DiscType.O + " ");
                } else if (splittedX[BOARD_HEIGHT - height].equals("1") && splittedO[height].equals("0")) {
                    System.out.print(DiscType.X + " ");
                }
            }
            System.out.println("");
        }
        // footer numbering
        System.out.print("  ");
        for (int width = 0; width < BOARD_WIDTH; width++) {
            System.out.print(" " + width);
        }
        System.out.println("");
    }


    public String convert(long bitRepresention) {
        String result = Long.toBinaryString(bitRepresention);
        String resultWithPadding = String.format("%49s", result).replaceAll(" ", "0");
        return produceBinaryString(resultWithPadding, BOARD_WIDTH, SEPARATOR);
    }

    private String produceBinaryString(String binary, int blockSize, String separator) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }

    public int getCounter() {
        return counter;
    }

    public long getMostRecentlyMove(DiscType discType) {
        return bitboardMap.get(discType);
    }
}
