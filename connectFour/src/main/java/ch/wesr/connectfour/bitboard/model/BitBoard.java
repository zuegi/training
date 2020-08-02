package ch.wesr.connectfour.bitboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BitBoard {

    public static int BOARD_WIDTH = 7;
    public static int BOARD_HEIGHT = 6;

    private int[] height = new int[]{0, 7, 14, 21, 28, 35, 42};
    private int counter;
    private long[] bitboard = new long[2];

    public static BitBoard create() {
        return new BitBoard();
    }

    public long makeMove(int column) {
        counter++;
        long move= 1L << height[column]++;
        bitboard[counter & 1] ^= move;
        return bitboard[counter & 1];
    }


    public String convert(long bitRepresention) {
        String result = Long.toBinaryString(bitRepresention);
        String resultWithPadding = String.format("%49s", result).replaceAll(" ", "0");  // 8-bit Long
//       return resultWithPadding;
        return printBinary(resultWithPadding, 7, "|");
    }

    private String printBinary(String binary, int blockSize, String separator) {

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

    public String convert() {
        return convert(this.bitboard[getCounter() & 1]);
    }
}
