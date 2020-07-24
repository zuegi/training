package ch.wesr.connectfour.model;

import java.util.ArrayList;
import java.util.List;

public class DownLeftUpRightDiagonal extends AbstractDiagonal{

    public DownLeftUpRightDiagonal(GameTable gameTable, Disc disc) {
        super(gameTable, disc);
        setDiagonal();
    }

    private void setDiagonal() {

        int startY = Math.max(disc.getYCoordinate() - 3, 0);
        int endY = Math.min(disc.getYCoordinate() + 3, gameTable.getMaxY());

        for(int y = startY; y <= endY ;y++) {

            int startX = Math.max(disc.getXCoordinate() - 3, 0);
            int endX = Math.min(disc.getXCoordinate() +3, gameTable.getMaxX());
            for (int x = startX; x <= endX; x++) {

                if (y - x == disc.getYCoordinate() - disc.getXCoordinate()) {
                    Disc disc1 = gameTable.getGametable()[y][x];
                    diagonal.add(disc1);
                }
            }
        }
    }
}
