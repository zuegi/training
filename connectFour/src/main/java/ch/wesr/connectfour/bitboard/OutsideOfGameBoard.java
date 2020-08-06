package ch.wesr.connectfour.bitboard;

public class OutsideOfGameBoard extends Exception {
    public OutsideOfGameBoard(String message) {
        super(message);
    }
}
