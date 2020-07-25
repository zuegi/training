package ch.wesr.connectfour.model;

import static ch.wesr.connectfour.model.GameConstant.THIS_NUMBER_WINS;

public class HorizontalStraight extends AbstractStraight{

    public HorizontalStraight(GameTable gameTable, Disc disc) {
        super(gameTable, disc);
        setHorizontal();
    }

    private void setHorizontal() {
        // ich will auf der y Achse über die x Werte durchiterieren
        int discCounter = 0;
        for (Disc disc1 : gameTable.getGametable()[disc.getYCoordinate()]) {
            if (disc1.getDiscType().equals(disc.getDiscType())) {
                Disc disc2 = gameTable.getGametable()[disc.getYCoordinate()][disc.getXCoordinate()];
                straight.add(disc2);
            }
        }
    }
}
