package ch.wesr.connectfour.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DownLeftUpRightDiagonalTest extends AbstractDiagonalTest{

    @Test
    public void testDownLeftUpRightWithTable5x4From3x1() {
        GameTable gameTable = new GameTable(5,4);
        Disc disc = new Disc(3, 1, DiscType.X);
        gameTable.setDisc(disc);

        DownLeftUpRightDiagonal downLeftUpRightDiagonal = new DownLeftUpRightDiagonal(gameTable, disc);
        List<Disc> diagonal = downLeftUpRightDiagonal.getDiagonal();
        printDiagonal(gameTable, diagonal);

        assertEquals(3, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }

    @Test
    public void testDownLeftUpRightWithTable5x4From1x3() {
        GameTable gameTable = new GameTable(5,4);
        Disc disc = new Disc(1, 3, DiscType.X);
        gameTable.setDisc(disc);

        DownLeftUpRightDiagonal downLeftUpRightDiagonal = new DownLeftUpRightDiagonal(gameTable, disc);
        List<Disc> diagonal = downLeftUpRightDiagonal.getDiagonal();
        printDiagonal(gameTable, diagonal);

        assertEquals(2, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }

    @Test
    public void testDownLeftUpRightWithTable7x7From1x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(1, 3, DiscType.X);
        gameTable.setDisc(disc);
        DownLeftUpRightDiagonal downLeftUpRightDiagonal = new DownLeftUpRightDiagonal(gameTable, disc);
        List<Disc> diagonal = downLeftUpRightDiagonal.getDiagonal();
        printDiagonal(gameTable, diagonal);
        // max 7 Discs aus der Diagonale, weil gametable 7,7 ist.
        assertEquals(5, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }


    @Test
    public void testDownLeftUpRightWithTable7x7From3x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(3, 3, DiscType.X);
        gameTable.setDisc(disc);
        DownLeftUpRightDiagonal downLeftUpRightDiagonal = new DownLeftUpRightDiagonal(gameTable, disc);
        List<Disc> diagonal = downLeftUpRightDiagonal.getDiagonal();
        printDiagonal(gameTable, diagonal);
        // max 7 Discs aus der Diagonale, weil gametable 7,7 ist.
        assertEquals(7, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }


    @Test
    public void testTopLeftRightDownDiagonalWithTable7x7From4x3() {
        GameTable gameTable = new GameTable(7,7);
        Disc disc = new Disc(4, 3, DiscType.X);
        gameTable.setDisc(disc);
        DownLeftUpRightDiagonal downLeftUpRightDiagonal = new DownLeftUpRightDiagonal(gameTable, disc);
        List<Disc> diagonal = downLeftUpRightDiagonal.getDiagonal();
        printDiagonal(gameTable, diagonal);
        assertEquals(6, diagonal.size());
        assertTrue(diagonal.contains(disc));
    }
}
