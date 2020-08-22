package ch.wesr.connectfour.bitboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintGame {

    public static final String SEPARATOR = " ";
    private final int[] startPositions = new int[]{6, 13, 20, 27, 34, 41, 48};

    public PrintGame() {
    }

    public void printBoard(Map<DiscType, Long> bitboardMap) {
        String stringO = convertBinaryToDiscTypeString(DiscType.O, bitboardMap);

        String stringX = convertBinaryToDiscTypeString(DiscType.X, bitboardMap);
        String mergedBitBoardString = merge(stringO, stringX);
        assert mergedBitBoardString != null;
        assert mergedBitBoardString.length() == 49;
        System.out.println(mergedBitBoardString);

        for (int height = BitBoard.BOARD_HEIGHT -1; height >= 0 ; height--) {
            System.out.print(height + "  ");
            for (int width = BitBoard.BOARD_WIDTH-1; width >= 0; width--) {
                int startPosition = startPositions[width];
                int position = startPosition -height;
                System.out.print(mergedBitBoardString.charAt(position) +" ");
            }
            System.out.println();
        }
        // footer numbering
        System.out.print("  ");
        for (int width = 0; width < BitBoard.BOARD_WIDTH; width++) {
            System.out.print(" " + width);
        }
        System.out.println();
    }


    public String convertBinaryToDiscTypeString(DiscType discType, Map<DiscType, Long> bitboardMap) {
        String binaryString = Long.toBinaryString(bitboardMap.get(discType));
        String formattedString = String.format("%49s", binaryString).replaceAll(" ", "0");
        return formattedString.replaceAll("1", discType.name());
    }

    public String merge(String sO, String sX) {

        if (sO.length() != sX.length()) {
            return null;
        }

        int stringLength = sO.length();
        StringBuilder mergedString = new StringBuilder();

        for (int i = 0; i < stringLength; i++) {
            String newChar = ".";
            int compare = Character.compare(sO.charAt(i), sX.charAt(i));

            if (compare != 0){


                if (String.valueOf(sO.charAt(i)).equals(DiscType.O.name())) {
                    newChar = String.valueOf(sO.charAt(i));
                }
                if (String.valueOf(sX.charAt(i)).equals(DiscType.X.name())) {
                    newChar = String.valueOf(sX.charAt(i));
                }

            }

            mergedString.append(newChar);
        }

        return mergedString.toString();
    }

    public String convert(long bitRepresention) {
        String result = Long.toBinaryString(bitRepresention);
        String resultWithPadding = String.format("%49s", result).replaceAll(" ", "0");
        return produceBinaryString(resultWithPadding, BitBoard.BOARD_WIDTH);
    }

    private String produceBinaryString(String binary, int blockSize) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return String.join(PrintGame.SEPARATOR, result);
    }

    public void printBoard(BitBoard bitBoard) {
        printBoard(bitBoard.getBitboardMap());
    }
}
