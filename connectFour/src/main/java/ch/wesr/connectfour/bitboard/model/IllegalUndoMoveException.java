package ch.wesr.connectfour.bitboard.model;

public class IllegalUndoMoveException extends Throwable {

    public IllegalUndoMoveException(String message) {
        super(message);
    }
}
