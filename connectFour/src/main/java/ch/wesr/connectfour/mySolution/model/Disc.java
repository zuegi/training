package ch.wesr.connectfour.mySolution.model;

import lombok.Data;

@Data
public class Disc {
    private int xCoordinate;
    private int yCoordinate;
    private DiscType discType;

    public Disc(int y, int x) {
        this.yCoordinate = y;
        this.xCoordinate = x;
    }

    public Disc(DiscType discType) {
        this.discType = discType;
    }

    public Disc(int y1, int x1, DiscType p) {
        this.yCoordinate = y1;
        this.xCoordinate = x1;
        this.discType = p;
    }
}
