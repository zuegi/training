package ch.wesr.connectfour.mySolution.model;


public class Player {
    private String name;
    private DiscType discType;
    private Game game;
    private boolean gameStartedByMe;
    private boolean nextPlayer = false;
    private Player joinedPlayer;

    public Player(String name, DiscType discType) {
        this.name = name;
        this.discType = discType;
    }

    public boolean dropStone(int y, int x) {
        Disc disc = new Disc(y, x);
        disc.setDiscType(this.discType);
        nextPlayer = false;
        return game.dropStone(disc);
    }

    public void printGameTable() {
        game.printGameTable();
    }

    public void startGame(int y, int x) {
        this.game = new Game(y, x);
        this.gameStartedByMe = true;
    }

    public void join(Player player) {
        this.game = player.getGame();
        this.joinedPlayer = player;
        player.joinedPlayer = this;
    }

    private Game getGame() {
        return this.game;
    }

    public boolean isGameStartedByMe() {
        return gameStartedByMe;
    }

    public String getName() {
        return name;
    }

    public boolean isNextPlayer() {
        return nextPlayer;
    }

    public Player getJoinedPlayer() {
        return joinedPlayer;
    }
}
