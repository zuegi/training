package ch.wesr.connectfour.mySolution.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStraight {

    protected GameTable gameTable;
    protected Disc disc;
    protected  List<Disc> straight = new ArrayList<>();

    public AbstractStraight(GameTable gameTable, Disc disc) {
        this.gameTable = gameTable;
        this.disc = disc;
    }

    public boolean checkConnectedFor(int numberOfDiscsPerDiskType) {
        long count = straight.stream()
                .filter(disc1 -> disc1.getDiscType().equals(disc.getDiscType()))
                .count();
        return count == numberOfDiscsPerDiskType;
    }

    public List<Disc> getStraight() {
        return straight;
    }

}
