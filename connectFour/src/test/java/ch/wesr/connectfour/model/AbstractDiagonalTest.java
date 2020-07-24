package ch.wesr.connectfour.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractDiagonalTest {

    protected void printGameTable(GameTable gameTable, List<Disc> discList) {
        discList.stream().forEach(disc1 -> {
            if (!disc1.getDiscType().equals(DiscType.X)) {
                disc1.setDiscType(DiscType.O);
            }
            gameTable.setDisc(disc1);
        });
        gameTable.printGameTable();
    }

}
