package ch.wesr.connectfour.model;

import static ch.wesr.connectfour.model.GameConstant.*;

public class GameTable {

    private Disc[][] gametable;
    private int maxX;
    private int maxY;

    public GameTable(int y, int x) {
        this.maxY = y-1;
        this.maxX = x-1;
        gametable = new Disc[y][x];

        for (int y1 = 0; y1 <= maxY; y1++) {
            for (int x1 = 0; x1 <= maxX; x1++) {
                Disc disc = new Disc(y1, x1, DiscType.P);
                gametable[y1][x1]=disc;
            }
        }
    }

    public Disc[][] getGametable() {
        return gametable;
    }

    public boolean isPale(Disc disc) {
        Disc discFromGameTable = gametable[disc.getYCoordinate()][disc.getXCoordinate()];
        return discFromGameTable.getDiscType() == DiscType.P;
    }

    public void setDisc(Disc disc) {
        gametable[disc.getYCoordinate()][disc.getXCoordinate()] = disc;
    }

    public boolean checkHorizontal(Disc disc) {
        // ich will auf der y Achse über die x Werte durchiterieren
        int discCounter = 0;
        for (Disc disc1 : gametable[disc.getYCoordinate()]) {
            if (discCounter == THIS_NUMBER_WINS) {
                return true;
            }
            if (disc1.getDiscType().equals(disc.getDiscType())) {
                discCounter++;
            }
        }
        return false;
    }

    public boolean checkVertical(Disc disc) {
        VerticalStraight verticalStraight = new VerticalStraight(this, disc);
        return verticalStraight.checkConnectedFor(THIS_NUMBER_WINS);
    }

    public boolean checkDiagonal(Disc disc) {
        UpLeftDownRightDiagonal upLeftDownRightDiagonal = new UpLeftDownRightDiagonal(this, disc);
        DownLeftUpRightDiagonal downLeftUpRightDiagonal = new DownLeftUpRightDiagonal(this, disc);

        return upLeftDownRightDiagonal.checkConnectedFor(THIS_NUMBER_WINS)
                || downLeftUpRightDiagonal.checkConnectedFor(THIS_NUMBER_WINS);

    }


    public void printGameTable() {
        for (int y = maxY; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 0; x <= maxX; x++) {
                System.out.print("|");

                if (gametable[y][x].getDiscType() == DiscType.P) {
                    System.out.print(" ");
                } else {
                    System.out.print(gametable[y][x].getDiscType());
                }
                if (x == maxX ) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        for (int i = 0; i <= maxX; i++) {
            if (i == 0) {
                System.out.print("  |");
            }
            System.out.print(i + "|");
            if (i == maxX) {
                System.out.println();
            }
        }
    }

    public boolean hasSolidGround(Disc disc) {
        if (disc.getYCoordinate() == 0) {
            return true;
        }
        int yCoordinate = disc.getYCoordinate();
        DiscType discType = gametable[yCoordinate - 1][disc.getXCoordinate()].getDiscType();
        if (discType.equals(discType.P)) {
            return false;
        }
        return true;
    }

    public boolean isValidPosition(Disc disc) {
        return (disc.getXCoordinate() >= 0 && disc.getXCoordinate() <= maxX) && (disc.getYCoordinate() >= 0 && disc.getYCoordinate() <= maxY);
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxX() {
        return maxX;
    }

}
