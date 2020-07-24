package ch.wesr.connectfour.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDiagonal {

    protected GameTable gameTable;
    protected Disc disc;
    protected  List<Disc> diagonal = new ArrayList<>();

    public AbstractDiagonal(GameTable gameTable, Disc disc) {
        this.gameTable = gameTable;
        this.disc = disc;
    }

    public boolean checkForDiagonalsFor(int numberOfDiscsPerDiskType) {
        long count = diagonal.stream()
                .filter(disc1 -> disc1.getDiscType().equals(disc.getDiscType()))
                .count();
        return count == numberOfDiscsPerDiskType;
    }

    public List<Disc> getDiagonal() {
        return diagonal;
    }

}
