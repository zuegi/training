package ch.wesr.connectfour.model;


public class Player {
    private String name;
    private DiscType discType;
    private Game game;

    public Player(String name, DiscType discType) {
        this.name = name;
        this.discType = discType;
    }

    public boolean dropStone(int y, int x) {
        Disc disc = new Disc(y, x);
        disc.setDiscType(this.discType);
        return game.dropStone(disc);
    }

    public void printGameTable() {
        game.printGameTable();
    }

    public void startGame(int y, int x) {
        this.game = new Game(y, x);
    }

    public void join(Player player) {
        this.game = player.getGame();
    }

    private Game getGame() {
        return this.game;
    }
}
