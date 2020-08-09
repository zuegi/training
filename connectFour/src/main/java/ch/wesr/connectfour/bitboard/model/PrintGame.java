package ch.wesr.connectfour.bitboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintGame {

    public static final String SEPARATOR = " ";

    public PrintGame() {
    }

    // TODO printBoard aus dieser Klasse extrahieren und in eigene Klasse verschieben
    // Single Responsibility Prinzip
    public void printBoard(Map<DiscType, Long> bitboardMap) {
        String bitBoardBinaryStringO = this.convert(bitboardMap.get(DiscType.O));
        String[] arrayOfColumnsO = bitBoardBinaryStringO.split(SEPARATOR);

        String bitBoardBinaryStringX = this.convert(bitboardMap.get(DiscType.X));
        String[] arrayOfColumnsX = bitBoardBinaryStringX.split(SEPARATOR);

        for (int height = BitBoard.BOARD_HEIGHT - 1; height >= 0; height--) {
            // left side numbering
            System.out.print(height + "  ");

            for (int width = BitBoard.BOARD_WIDTH - 1; width >= 0; width--) {
                String[] splittedO = arrayOfColumnsO[width].split("");
                String[] splittedX = arrayOfColumnsX[width].split("");

                if (splittedO[BitBoard.BOARD_HEIGHT - height].equals(splittedX[BitBoard.BOARD_HEIGHT - height])) {
                    System.out.print(". ");
                } else if (splittedO[BitBoard.BOARD_HEIGHT - height].equals("1") && splittedX[height].equals("0")) {
                    System.out.print(DiscType.O + " ");
                } else if (splittedX[BitBoard.BOARD_HEIGHT - height].equals("1") && splittedO[height].equals("0")) {
                    System.out.print(DiscType.X + " ");
                }
            }
            System.out.println("");
        }
        // footer numbering
        System.out.print("  ");
        for (int width = 0; width < BitBoard.BOARD_WIDTH; width++) {
            System.out.print(" " + width);
        }
        System.out.println("");
    }


    public String convert(long bitRepresention) {
        String result = Long.toBinaryString(bitRepresention);
        String resultWithPadding = String.format("%49s", result).replaceAll(" ", "0");
        return produceBinaryString(resultWithPadding, BitBoard.BOARD_WIDTH, SEPARATOR);
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
}
