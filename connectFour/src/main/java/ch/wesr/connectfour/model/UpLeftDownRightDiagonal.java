package ch.wesr.connectfour.model;

import java.util.ArrayList;
import java.util.List;

public class UpLeftDownRightDiagonal extends AbstractDiagonal {


    public UpLeftDownRightDiagonal(GameTable gameTable, Disc disc) {
        super(gameTable, disc);
        setUpLeftRightDown();
    }

    public void setUpLeftRightDown() {
        // finde heraus in welchen beiden Diagonalen die disc Koordinaten liegen
        int startY = Math.min(disc.getYCoordinate() + 3, gameTable.getMaxY());
        int endY = Math.max(disc.getYCoordinate() - 3, 0);

        for(int y = startY; y >= endY ;y--) {

            int startX = Math.max(disc.getXCoordinate() - (startY - disc.getYCoordinate()),0);
            int endX = Math.min(disc.getXCoordinate() + 3, gameTable.getMaxX());
            for (int x = startX; x <= endX; x++) {

                if (y + x == disc.getYCoordinate() + disc.getXCoordinate()) {
                    Disc disc1 = gameTable.getGametable()[y][x];
                    diagonal.add(disc1);
                }
            }
        }
    }
}
