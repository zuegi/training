package ch.wesr.connectfour.bitboard;


import ch.wesr.connectfour.bitboard.model.*;
import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSpec {

    Player zuegiAsX;
    Player grootAsO;
    Game game;

//
//    @Test
//    void letsPlayTwoBotsAgainst() {
//        initalizeValidGame();
//        zuegi.setMaximizer(true);
//        groot.setMaximizer(false);
//        List<Player> playerList = new ArrayList<>();
//        int counter = 0;
//        while (true) {
//            if ((counter++ & 1) == 0) {
//                zuegi.findBestMove();
//            } else {
//                groot.findBestMove();
//            }
//        }
//
//    }
//
    @Test
    void playAgainstFirstBot() {
       initalizeValidGameNextMoveHasZuegiAsX();

        zuegiAsX.makeMove(3);
        // then bot player should make move on its own
        grootAsO.makeBestMove(); // Groot
        zuegiAsX.makeMove(3);
        grootAsO.makeBestMove();
        zuegiAsX.makeMove(3);
        grootAsO.makeBestMove();
        zuegiAsX.makeMove(2);
        assertThrows(GameOverException.class, () -> grootAsO.makeBestMove());
    }

    @Test
    void illegalUndoMove() {
        // given
        initalizeValidGameNextMoveHasZuegiAsX();
        zuegiAsX.makeMove(3);
        grootAsO.makeMove(2);
        zuegiAsX.makeMove(2);
        // when then
        assertThrows(IllegalUndoMoveException.class, () -> grootAsO.undoMove(2));

    }

    @Test
    void makeValidUndoMove() {
        // given
        initalizeValidGameNextMoveHasZuegiAsX();

        // when
        zuegiAsX.makeMove(0);

        // then
        assertTrue(zuegiAsX.undoMove(0));
    }

    @Test
    void invalidMoveMinusColumn() {
        initalizeValidGameNextMoveHasZuegiAsX();
        assertThrows(OutsideOfGameBoardException.class, () -> zuegiAsX.makeMove(-1));
    }

    @Test
    void columnOutsideBoard() {
        initalizeValidGameNextMoveHasZuegiAsX();
        assertThrows(OutsideOfGameBoardException.class, () -> zuegiAsX.makeMove(8));
    }

    @Test
    void testInvalidMoveAboveBoardHeigt() {
        initalizeValidGameNextMoveHasZuegiAsX();
        // given
        // . . . X . . .
        // . . . O . . .
        // . . . X . . .
        // . . . O . . .
        // . . . X . . .
        // . . O X O . .
        // 0 1 2 3 4 5 6
        zuegiAsX.makeMove(2);
        grootAsO.makeMove(3);
        zuegiAsX.makeMove(4);
        grootAsO.makeMove(3);
        zuegiAsX.makeMove(3);
        grootAsO.makeMove(3);
        zuegiAsX.makeMove(3);
        grootAsO.makeMove(3);

        // then
        assertThrows(OutsideOfGameBoardException.class, () -> zuegiAsX.makeMove(3));

    }
//


    @DisplayName("player O (Zuegi) wins the game vertical")
    @Test
    void playerOwinsTheGameVertical() {
        // given
       initalizeValidGameNextMoveHasZuegiAsX();
        // when
        zuegiAsX.makeMove(0);
        grootAsO.makeMove(1);
        zuegiAsX.makeMove(0);
        grootAsO.makeMove(2);
        zuegiAsX.makeMove(0);
        grootAsO.makeMove(3);
        // then
        assertThrows(GameOverException.class, () -> zuegiAsX.makeMove(0));
    }

    @DisplayName("player X (Groot) wins the game horizontal")
    @Test
    void playerXwinsTheGameHorizontal() {
        // given
       initalizeValidGameNextMoveHasZuegiAsX();
        // when
        zuegiAsX.makeMove(0);
        grootAsO.makeMove(1);
        zuegiAsX.makeMove(2);
        grootAsO.makeMove(3);
        zuegiAsX.makeMove(3);
        grootAsO.makeMove(4);
        zuegiAsX.makeMove(1);
        grootAsO.makeMove(5);
        zuegiAsX.makeMove(2);
        // then
        assertThrows(GameOverException.class, () -> grootAsO.makeMove(6));
    }

    @DisplayName("player X wants to make 2 moves")
    @Test
    void playerXWantsToMake2Moves() {
        // given
       initalizeValidGameNextMoveHasZuegiAsX();
        // when
        grootAsO.makeMove(0);
        assertThrows(IllegalArgumentException.class, () -> grootAsO.makeMove(1));
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
        initalizeValidGameNextMoveHasZuegiAsX();

        // when
        assertTrue(zuegiAsX.makeMove(2));

        // then
//        assertEquals(expectedBitboardLong, grootAsO.getMostRecentlyMove(), () -> "move should be " + expectedBitboardLong);
    }

    private void initalizeValidGameNextMoveHasZuegiAsX() {
        zuegiAsX = new Player(DiscType.O, "Zuegi");
        game = zuegiAsX.startGame();
        game.printMoves(true);
        grootAsO = new Player(DiscType.X, "Groot");
        grootAsO.joinGame(game);
    }
    @DisplayName("Two players start a valid game")
    @Test
    void playerOinitalizesTheGame() {
        // given
        zuegiAsX = new Player(DiscType.O, "Zuegi");
        game = zuegiAsX.startGame();
        grootAsO = new Player(DiscType.X, "Groot");
        grootAsO.joinGame(game);
        // then
        assertTrue(grootAsO.makeMove(2));


    }
}
