package ch.wesr.connectfour.bitboard.model.exception;

public class OutsideOfGameBoardException extends Exception {
    public OutsideOfGameBoardException(String message) {
        super(message);
    }
}
