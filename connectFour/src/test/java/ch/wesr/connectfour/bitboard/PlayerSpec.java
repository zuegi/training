package ch.wesr.connectfour.bitboard;


import ch.wesr.connectfour.bitboard.model.*;
import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSpec {

//    Player zuegi;
//    Player groot;
//    Game game;
//
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
//    @Test
//    void playAgainstFirstBot() {
//       initalizeValidGame();
//        // playerO starts the game
//        zuegi.makeMove(3); // Zuegi
//        // then bot player should make move on its own
//        groot.findBestMove(); // Groot
//
//        zuegi.makeMove(3);
//        groot.findBestMove();
//        zuegi.makeMove(3);
//        groot.findBestMove();
//        zuegi.makeMove(2);
//        assertThrows(GameOverException.class, () -> groot.findBestMove());
//    }
//
//    @Test
//    void illegalUndoMove() {
//        // given
//        initalizeValidGame();
//        zuegi.makeMove(3);
//        groot.makeMove(2);
//        zuegi.makeMove(2);
//        // when then
//        assertThrows(IllegalUndoMoveException.class, () -> groot.undoMove(2));
//
//    }
//
//    @Test
//    void makeUndoMove() {
//        // given
//        initalizeValidGame();
//        game.printMoves(true);
//        game.printGame();
//
//        long mostRecentlyMove = zuegi.getMostRecentlyMove();
//        // when
//        zuegi.makeMove(0);
//
//        // then
//        assertEquals(mostRecentlyMove, zuegi.undoMove(0), () -> "move should be " + mostRecentlyMove);
//    }
//
//    @Test
//    void invalidMoveMinusColumn() {
//        initalizeValidGame();
//        assertThrows(OutsideOfGameBoardException.class, () -> zuegi.makeMove(-1));
//    }
//
//    @Test
//    void columnOutsideBoard() {
//        initalizeValidGame();
//        assertThrows(OutsideOfGameBoardException.class, () -> zuegi.makeMove(8));
//    }
//
//    @Test
//    void testInvalidMoveAboveBoardHeigt() {
//        initalizeValidGame();
//        // given
//        // . . . X . . .
//        // . . . O . . .
//        // . . . X . . .
//        // . . . O . . .
//        // . . . X . . .
//        // . . O X O . .
//        // 0 1 2 3 4 5 6
//        zuegi.makeMove(2);
//        groot.makeMove(3);
//        zuegi.makeMove(4);
//        groot.makeMove(3);
//        zuegi.makeMove(3);
//        groot.makeMove(3);
//        zuegi.makeMove(3);
//        groot.makeMove(3);
//        game.printGame();
//
//        // then
//        assertThrows(OutsideOfGameBoardException.class, () -> zuegi.makeMove(3));
//
//    }
//
//    private void initalizeValidGame() {
//        zuegi = new Player(DiscType.O, "Zuegi");
//        game = zuegi.startGame();
//        game.printMoves(true);
//        groot = new Player(DiscType.X, "Groot");
//        groot.joinGame(game);
//    }
//
//    @DisplayName("player O (Zuegi) wins the game vertical")
//    @Test
//    void playerOwinsTheGameVertical() {
//        // given
//       initalizeValidGame();
//        // when
//        zuegi.makeMove(0);
//        groot.makeMove(1);
//        zuegi.makeMove(0);
//        groot.makeMove(2);
//        zuegi.makeMove(0);
//        groot.makeMove(3);
//        // then
//        assertThrows(GameOverException.class, () -> zuegi.makeMove(0));
//    }
//
//    @DisplayName("player X (Groot) wins the game horizontal")
//    @Test
//    void playerXwinsTheGameHorizontal() {
//        // given
//       initalizeValidGame();
//        // when
//        zuegi.makeMove(0);
//        groot.makeMove(1);
//        zuegi.makeMove(2);
//        groot.makeMove(3);
//        zuegi.makeMove(3);
//        groot.makeMove(4);
//        zuegi.makeMove(1);
//        groot.makeMove(5);
//        zuegi.makeMove(2);
//        // then
//        assertThrows(GameOverException.class, () -> groot.makeMove(6));
//    }
//
//    @DisplayName("player X wants to make 2 moves")
//    @Test
//    void playerXWantsToMake2Moves() {
//        // given
//       initalizeValidGame();
//        // when
//        groot.makeMove(0);
//        assertThrows(IllegalArgumentException.class, () -> groot.makeMove(1));
//    }
//
//    @DisplayName("player X join a void game")
//    @Test
//    void playerXJoinVoidGame() {
//        // given a void game - no firstPlayer set
//        Game game = new Game();
//        Player playerX = new Player(DiscType.X, "Zuegi");
//        // when - then
//        assertThrows(IllegalArgumentException.class, () -> playerX.joinGame(game));
//    }
//
//    @DisplayName("2 players X can not join the same game")
//    @Test
//    void playerXOpensANewGameAndAnotherPlayerXWantsToJoin() {
//        // given
//        Player playerX = new Player(DiscType.X, "Zuegi");
//        Game game = playerX.startGame();
//
//        //when
//        Player anotherPlayerX = new Player(DiscType.X, "Groot");
//
//        // then
//        assertThrows(IllegalArgumentException.class, () -> anotherPlayerX.joinGame(game));
//    }
//
//    @DisplayName("player O initalizes - player X opens the game")
//    @Test
//    void playerOinitalizesPlayerXOpensTheGame() {
//        // given
//        long expectedBitboardLong = 16384L;
//        initalizeValidGame();
//
//        // when
//        groot.makeMove(2);
//
//        // then
//        assertEquals(expectedBitboardLong, groot.getMostRecentlyMove(), () -> "move should be " + expectedBitboardLong);
//    }
//
//    @DisplayName("Two players start a valid game")
//    @Test
//    void playerOinitalizesTheGame() {
//        // given
//        long expectedBitboardLong = 16384L;
//        Player playerO = new Player(DiscType.O, "Zuegi");
//        Game game = playerO.startGame();
//        game.printMoves(false);
//        Player playerX = new Player(DiscType.X, "Groot");
//
//        // when
//        playerX.joinGame(game);
//        playerO.makeMove(2);
//
//        // then
//        assertEquals(expectedBitboardLong, playerO.getMostRecentlyMove(), () -> "move should be " + expectedBitboardLong);
//    }
}
