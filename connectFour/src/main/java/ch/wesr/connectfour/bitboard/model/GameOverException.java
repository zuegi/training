package ch.wesr.connectfour.bitboard.model;

public class GameOverException extends RuntimeException {

    public GameOverException(String game_over) {
        super(game_over);
    }
}
