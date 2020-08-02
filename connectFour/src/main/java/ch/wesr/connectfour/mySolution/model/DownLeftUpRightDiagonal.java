package ch.wesr.connectfour.mySolution.model;

import static ch.wesr.connectfour.mySolution.model.GameConstant.MAX_FIELDS_TO_CHECK;

public class DownLeftUpRightDiagonal extends AbstractStraight {

    public DownLeftUpRightDiagonal(GameTable gameTable, Disc disc) {
        super(gameTable, disc);
        setDiagonal();
    }

    private void setDiagonal() {

        int startY = Math.max(disc.getYCoordinate() - MAX_FIELDS_TO_CHECK, 0);
        int endY = Math.min(disc.getYCoordinate() + MAX_FIELDS_TO_CHECK, gameTable.getMaxY());

        for(int y = startY; y <= endY ;y++) {

            int startX = Math.max(disc.getXCoordinate() - MAX_FIELDS_TO_CHECK, 0);
            int endX = Math.min(disc.getXCoordinate() + MAX_FIELDS_TO_CHECK, gameTable.getMaxX());
            for (int x = startX; x <= endX; x++) {

                if (y - x == disc.getYCoordinate() - disc.getXCoordinate()) {
                    Disc disc1 = gameTable.getGametable()[y][x];
                    straight.add(disc1);
                }
            }
        }
    }
}
