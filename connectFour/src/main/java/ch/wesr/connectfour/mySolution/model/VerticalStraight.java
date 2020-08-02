package ch.wesr.connectfour.mySolution.model;

public class VerticalStraight extends AbstractStraight {


    public VerticalStraight(GameTable gameTable, Disc disc) {
        super(gameTable, disc);
        setVertical();
    }

    public void setVertical() {
        for (Disc[] discs : gameTable.getGametable()) {
            if (discs[disc.getXCoordinate()].getDiscType().equals(disc.getDiscType())) {
                Disc disc1 = gameTable.getGametable()[disc.getYCoordinate()][disc.getXCoordinate()];
                straight.add(disc1);
            }
        }
    }
}
