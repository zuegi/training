package ch.wesr.connectfour.mySolution.model;

public class Game {

    public static final String POSITON_ALLREADY_OCCUPIED = "Positon allready occupied";
    public static final String THE_PLAYER_IS_NOT_ALLOWED_TO_LAY_DISCS = "the player is not allowed to lay discs";
    private final GameTable gameTable;
    private DiscType currentDiscType;

    public Game(int y, int x) {
        gameTable = new GameTable(y, x);
    }

    public boolean dropStone(Disc disc) {
        if (currentDiscType != null && currentDiscType.equals(disc.getDiscType())) {
            throw new UnsupportedOperationException(THE_PLAYER_IS_NOT_ALLOWED_TO_LAY_DISCS + " " +disc.getDiscType());
        }

        if (! gameTable.isValidPosition(disc)) {
            throw new UnsupportedOperationException();
        }

        if (gameTable.hasSolidGround(disc) && gameTable.isPale(disc)) {
            gameTable.setDisc(disc);
            currentDiscType = disc.getDiscType();
        } else {
            throw new UnsupportedOperationException(POSITON_ALLREADY_OCCUPIED);
        }

        return checkWinner(disc);
    }

    private boolean checkWinner(Disc disc) {
        // aus den x,y
        return gameTable.checkHorizontal(disc) ||
        gameTable.checkVertical(disc) ||
        gameTable.checkDiagonal(disc);
    }

    public void printGameTable() {
        gameTable.printGameTable();
    }

    public Disc[][] getGameTable() {
        return gameTable.getGametable();
    }
}
