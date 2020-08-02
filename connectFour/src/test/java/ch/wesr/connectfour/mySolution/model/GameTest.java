package ch.wesr.connectfour.mySolution.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    static Game game;
    static Player playerO;
    static Player playerX;

    @BeforeEach
    public void initializeGame() {
        playerO = new Player("player 1", DiscType.O);
        playerO.startGame(7,7);
        playerX = new Player("player 2", DiscType.X);
        playerX.join(playerO);
    }

    @Test
    public void testPlayerOWonDownLeftUpRightDiagonal() {
        dropStonesForLeftRightDiagonal();
        assertTrue(playerO.dropStone(3, 3));
        playerX.printGameTable();
    }

    @Test
    public void testPlayerXWonUpLeftDownRightDiagonal() {
        dropStonesForLeftRightDiagonal();
        assertFalse(playerO.dropStone(3, 2));
        assertTrue(playerX.dropStone(3, 0));
    }

    private void dropStonesForLeftRightDiagonal() {

        /*
        * 3 |X| | |O| | |
        * 2 |O|X|O|X| | |
        * 1 |X|O|X|O| | |
        * 0 |O|O|X|X| | |
        * */

        assertFalse(playerO.dropStone(0, 0));
        assertFalse(playerX.dropStone(1, 0));
        assertFalse(playerO.dropStone(0, 1));
        assertFalse(playerX.dropStone(0, 2));
        assertFalse(playerO.dropStone(2, 0));
        assertFalse(playerX.dropStone(0, 3));
        assertFalse(playerO.dropStone(1, 3));
        assertFalse(playerX.dropStone(1, 2));
        assertFalse(playerO.dropStone(1, 1));
        assertFalse(playerX.dropStone(2, 1));
        assertFalse(playerO.dropStone(2, 2));
        assertFalse(playerX.dropStone(2, 3));

        // fourth row we will do in th test
    }

    @Test
    public void testPlayer1WonVertical() {
        assertFalse(playerO.dropStone(0,1));
        assertFalse(playerX.dropStone(0,2));
        assertFalse(playerO.dropStone(1,1));
        assertFalse(playerX.dropStone(1,2));
        assertFalse(playerO.dropStone(2,1));
        assertFalse(playerX.dropStone(2, 2));
        assertTrue(playerO.dropStone(3, 1));
    }

    @Test
    public void testPlayer1WonHorizontal() {
        assertFalse(playerO.dropStone(0,2));
        assertFalse(playerX.dropStone(1,2));
        assertFalse(playerO.dropStone(0,3));
        assertFalse(playerX.dropStone(1,3));
        assertFalse(playerO.dropStone(0,4));
        assertFalse(playerX.dropStone(1, 4));
        assertTrue(playerO.dropStone(0, 5));
    }

    @Test
    public void testDropStoneOutsideOfTable() {
        assertThrows(UnsupportedOperationException.class, () -> {
            playerO.dropStone(0,8);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            playerO.dropStone(8,0);
        });
    }

    @Test
    public void testSolidGround() {
        playerO.dropStone(0,3);
        playerX.dropStone(1, 3);
    }

    @Test
    public void testNotSolidUnderground() {
        // given when then
        assertThrows(UnsupportedOperationException.class, () -> {
            playerO.dropStone(1,3);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            playerO.dropStone(3,3);
        });
    }

    @Test
    public void testValidStones() {
        playerO.dropStone(0,3);
        playerX.dropStone(0,4);
    }

    @Test
    public void testDropStoneOnIllegalCoordinates() {
        // given to players with the same coordinates
        playerO.dropStone(0,2);

        assertThrows(UnsupportedOperationException.class, () -> {
            playerX.dropStone(0,2);
        });
    }

    @Test
    public void testRightPlayerHasItsTurn() {
        // given
        playerO.dropStone(0,2);
        // when - then
        assertThrows(UnsupportedOperationException.class, () -> {
            playerO.dropStone(0,3);
        });

    }

    @Test
    public void testCreateGame() {
        Game game = new Game(7, 7);
        assertTrue(game instanceof Game);
    }

}
