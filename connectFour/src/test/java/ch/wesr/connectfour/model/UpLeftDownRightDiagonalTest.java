package ch.wesr.connectfour.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpLeftDownRightDiagonalTest extends AbstractDiagonalTest {

    @Test
    public void testUpLeftDownRightWithTable5x4From3x1() {
        GameTable gameTable = new GameTable(5,4);
        Disc disc = new Disc(3, 1, DiscType.X);
        gameTable.setDisc(disc);
        UpLeftDownRightDiagonal upLeftDownRightDiagonal = new UpLeftDownRightDiagonal(gameTable, disc);
        List<Disc> diagonal = upLeftDownRightDiagonal.getDiagonal();
        printGameTable(gameTable, diagonal);

        assertEquals(4, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }

    @Test
    public void testUpLeftDownRightWithTable5x4From1x3() {
        GameTable gameTable = new GameTable(5,4);
        Disc disc = new Disc(1, 3, DiscType.X);
        gameTable.setDisc(disc);
        UpLeftDownRightDiagonal upLeftDownRightDiagonal = new UpLeftDownRightDiagonal(gameTable, disc);
        List<Disc> diagonal = upLeftDownRightDiagonal.getDiagonal();
        printGameTable(gameTable, diagonal);

        assertEquals(4, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }

    @Test
    public void testUpLeftDownRightWithTable7x7From1x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(1, 3, DiscType.X);
        gameTable.setDisc(disc);
        UpLeftDownRightDiagonal upLeftDownRightDiagonal = new UpLeftDownRightDiagonal(gameTable, disc);
        List<Disc> diagonal = upLeftDownRightDiagonal.getDiagonal();
        printGameTable(gameTable, diagonal);

        assertEquals(5, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }

    @Test
    public void testUpLeftDownRightWithTable7x7From3x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(3, 3, DiscType.X);
        gameTable.setDisc(disc);
        UpLeftDownRightDiagonal upLeftDownRightDiagonal = new UpLeftDownRightDiagonal(gameTable, disc);
        List<Disc> diagonal = upLeftDownRightDiagonal.getDiagonal();
        printGameTable(gameTable, diagonal);

        assertEquals(7, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }


    @Test
    public void testUpLeftDownRightWithTable7x7From4x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(4, 3, DiscType.X);
        gameTable.setDisc(disc);
        UpLeftDownRightDiagonal upLeftDownRightDiagonal = new UpLeftDownRightDiagonal(gameTable, disc);
        List<Disc> diagonal = upLeftDownRightDiagonal.getDiagonal();
        printGameTable(gameTable, diagonal);

        assertEquals(6, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }

}
