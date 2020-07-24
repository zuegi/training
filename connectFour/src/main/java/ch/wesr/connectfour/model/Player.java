package ch.wesr.connectfour.model;


public class Player {
    private String name;
    private DiscType discType;
    private Game game;

    public Player(String name, DiscType discType, Game game) {
        this.name = name;
        this.discType = discType;
        this.game = game;
    }

    public boolean dropStone(int y, int x) {
        Disc disc = new Disc(y, x);
        disc.setDiscType(this.discType);
        return game.dropStone(disc);
    }

    public void printGameTable() {
        game.printGameTable();
    }
}
