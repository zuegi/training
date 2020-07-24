package ch.wesr.connectfour.model;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTableTest {

    @Test
    public void testTopLeftRightDownDiagonalWithTable5x4From3x1() {
        GameTable gameTable = new GameTable(5,4);
        Disc disc = new Disc(3, 1, DiscType.X);
        gameTable.setDisc(disc);
        List<Disc> topLeftRightDownDiagonal = gameTable.getTopLeftRightDownDiagonal(disc);
        printGameTable(gameTable, topLeftRightDownDiagonal);

        assertEquals(4, topLeftRightDownDiagonal.size());
        assertTrue(topLeftRightDownDiagonal.contains(disc));
    }

    @Test
    public void testTopLeftRightDownDiagonalWithTable5x4From1x4() {
        GameTable gameTable = new GameTable(5,4);
        Disc disc = new Disc(1, 3, DiscType.X);
        gameTable.setDisc(disc);
        List<Disc> topLeftRightDownDiagonal = gameTable.getTopLeftRightDownDiagonal(disc);
        printGameTable(gameTable, topLeftRightDownDiagonal);

        assertEquals(4, topLeftRightDownDiagonal.size());
        assertTrue(topLeftRightDownDiagonal.contains(disc));
    }

    @Test
    public void testTopLeftRightDownDiagonalWithTable7x7From1x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(1, 3, DiscType.X);
        gameTable.setDisc(disc);
        List<Disc> topLeftRightDownDiagonal = gameTable.getTopLeftRightDownDiagonal(disc);
        printGameTable(gameTable, topLeftRightDownDiagonal);
        // max 7 Discs aus der Diagonale, weil gametable 7,7 ist.
        assertEquals(5, topLeftRightDownDiagonal.size());
        assertTrue(topLeftRightDownDiagonal.contains(disc));
    }

    @Test
    public void testTopLeftRightDownDiagonalWithTable7x7From3x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(3, 3, DiscType.X);
        gameTable.setDisc(disc);
        List<Disc> topLeftRightDownDiagonal = gameTable.getTopLeftRightDownDiagonal(disc);
        printGameTable(gameTable, topLeftRightDownDiagonal);
        // max 7 Discs aus der Diagonale, weil gametable 7,7 ist.
        assertEquals(7, topLeftRightDownDiagonal.size());
        assertTrue(topLeftRightDownDiagonal.contains(disc));
    }

    @Test
    public void testTopLeftRightDownDiagonalWithTable7x7From4x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(4, 3, DiscType.X);
        gameTable.setDisc(disc);
        List<Disc> topLeftRightDownDiagonal = gameTable.getTopLeftRightDownDiagonal(disc);
        // max 7 Discs aus der Diagonale, weil gametable 7,7 ist.
        printGameTable(gameTable, topLeftRightDownDiagonal);
        assertEquals(6, topLeftRightDownDiagonal.size());
        assertTrue(topLeftRightDownDiagonal.contains(disc));
    }

    private void printGameTable(GameTable gameTable, List<Disc> discList) {
        discList.stream().forEach(disc1 -> {
            if (!disc1.getDiscType().equals(DiscType.X)) {
                disc1.setDiscType(DiscType.O);
            }
            gameTable.setDisc(disc1);
        });
        gameTable.printGameTable();
    }

}
