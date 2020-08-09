package ch.wesr.connectfour.bitboard.model.exception;

public class IllegalUndoMoveException extends Throwable {

    public IllegalUndoMoveException(String message) {
        super(message);
    }
}
