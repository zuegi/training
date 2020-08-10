package ch.wesr.connectfour.bitboard;


import ch.wesr.connectfour.bitboard.model.*;
import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSpec {

    Player playerO;
    Player playerX;
    Game game;

    @Disabled
    @Test
    void playAgainstFirstBot() {
       initalizeValidGame();
        // playerO starts the game
        playerO.makeMove(3);
        // then bot player should make move on its own
        playerX.findBestMove();

        playerO.makeMove(3);
        playerX.findBestMove();
        playerO.makeMove(3);
        playerX.findBestMove();
        playerO.makeMove(2);
        playerX.findBestMove();
    }

    @Test
    void illegalUndoMove() {
        // given
        initalizeValidGame();
        playerO.makeMove(3);
        playerX.makeMove(2);
        playerO.makeMove(2);
        // when then
        assertThrows(IllegalUndoMoveException.class, () -> playerX.undoMove(2));

    }

    @Test
    void makeUndoMove() {
        // given
        initalizeValidGame();
        game.printMoves(true);
        game.printGame();

        long mostRecentlyMove = playerO.getMostRecentlyMove();
        // when
        playerO.makeMove(0);

        // then
        assertEquals(mostRecentlyMove, playerO.undoMove(0), () -> "move should be " + mostRecentlyMove);
    }

    @Test
    void invalidMoveMinusColumn() {
        initalizeValidGame();
        assertThrows(OutsideOfGameBoardException.class, () -> playerO.makeMove(-1));
    }

    @Test
    void columnOutsideBoard() {
        initalizeValidGame();
        assertThrows(OutsideOfGameBoardException.class, () -> playerO.makeMove(8));
    }

    @Test
    void testInvalidMoveAboveBoardHeigt() {
        initalizeValidGame();
        // given
        // . . . X . . .
        // . . . O . . .
        // . . . X . . .
        // . . . O . . .
        // . . . X . . .
        // . . O X O . .
        // 0 1 2 3 4 5 6
        playerO.makeMove(2);
        playerX.makeMove(3);
        playerO.makeMove(4);
        playerX.makeMove(3);
        playerO.makeMove(3);
        playerX.makeMove(3);
        playerO.makeMove(3);
        playerX.makeMove(3);
        game.printGame();

        // then
        assertThrows(OutsideOfGameBoardException.class, () -> playerO.makeMove(3));

    }

    private void initalizeValidGame() {
        playerO = new Player(DiscType.O, "Zuegi");
        game = playerO.startGame();
        game.printMoves(true);
        playerX = new Player(DiscType.X, "Groot");
        playerX.joinGame(game);
    }

    @DisplayName("player O (Zuegi) wins the game vertical")
    @Test
    void playerOwinsTheGameVertical() {
        // given
       initalizeValidGame();
        // when
        playerO.makeMove(0);
        playerX.makeMove(1);
        playerO.makeMove(0);
        playerX.makeMove(2);
        playerO.makeMove(0);
        playerX.makeMove(3);
        // then
        assertThrows(GameOverException.class, () -> playerO.makeMove(0));
    }

    @DisplayName("player X (Groot) wins the game horizontal")
    @Test
    void playerXwinsTheGameHorizontal() {
        // given
       initalizeValidGame();
        // when
        playerO.makeMove(0);
        playerX.makeMove(1);
        playerO.makeMove(2);
        playerX.makeMove(3);
        playerO.makeMove(3);
        playerX.makeMove(4);
        playerO.makeMove(1);
        playerX.makeMove(5);
        playerO.makeMove(2);
        // then
        assertThrows(GameOverException.class, () -> playerX.makeMove(6));
    }

    @DisplayName("player X wants to make 2 moves")
    @Test
    void playerXWantsToMake2Moves() {
        // given
       initalizeValidGame();
        // when
        playerX.makeMove(0);
        assertThrows(IllegalArgumentException.class, () -> playerX.makeMove(1));
    }

    @DisplayName("player X join a void game")
    @Test
    void playerXJoinVoidGame() {
        // given a void game - no firstPlayer set
        Game game = new Game();
        Player playerX = new Player(DiscType.X, "Zuegi");
        // when - then
        assertThrows(IllegalArgumentException.class, () -> playerX.joinGame(game));
    }

    @DisplayName("2 players X can not join the same game")
    @Test
    void playerXOpensANewGameAndAnotherPlayerXWantsToJoin() {
        // given
        Player playerX = new Player(DiscType.X, "Zuegi");
        Game game = playerX.startGame();

        //when
        Player anotherPlayerX = new Player(DiscType.X, "Groot");

        // then
        assertThrows(IllegalArgumentException.class, () -> anotherPlayerX.joinGame(game));
    }

    @DisplayName("player O initalizes - player X opens the game")
    @Test
    void playerOinitalizesPlayerXOpensTheGame() {
        // given
        long expectedBitboardLong = 16384L;
        initalizeValidGame();

        // when
        playerX.makeMove(2);

        // then
        assertEquals(expectedBitboardLong, playerX.getMostRecentlyMove(), () -> "move should be " + expectedBitboardLong);
    }

    @DisplayName("Two players start a valid game")
    @Test
    void playerOinitalizesTheGame() {
        // given
        long expectedBitboardLong = 16384L;
        Player playerO = new Player(DiscType.O, "Zuegi");
        Game game = playerO.startGame();
        game.printMoves(false);
        Player playerX = new Player(DiscType.X, "Groot");

        // when
        playerX.joinGame(game);
        playerO.makeMove(2);

        // then
        assertEquals(expectedBitboardLong, playerO.getMostRecentlyMove(), () -> "move should be " + expectedBitboardLong);
    }
}
