package ch.wesr.connectfour.mySolution.model;

import static ch.wesr.connectfour.mySolution.model.GameConstant.*;

public class UpLeftDownRightDiagonal extends AbstractStraight {


    public UpLeftDownRightDiagonal(GameTable gameTable, Disc disc) {
        super(gameTable, disc);
        setUpLeftRightDown();
    }

    public void setUpLeftRightDown() {
        // finde heraus in welchen beiden Diagonalen die disc Koordinaten liegen
        int startY = Math.min(disc.getYCoordinate() + MAX_FIELDS_TO_CHECK, gameTable.getMaxY());
        int endY = Math.max(disc.getYCoordinate() - MAX_FIELDS_TO_CHECK, 0);

        for(int y = startY; y >= endY ;y--) {

            int startX = Math.max(disc.getXCoordinate() - (startY - disc.getYCoordinate()),0);
            int endX = Math.min(disc.getXCoordinate() + MAX_FIELDS_TO_CHECK, gameTable.getMaxX());
            for (int x = startX; x <= endX; x++) {

                if (y + x == disc.getYCoordinate() + disc.getXCoordinate()) {
                    Disc disc1 = gameTable.getGametable()[y][x];
                    straight.add(disc1);
                }
            }
        }
    }
}
