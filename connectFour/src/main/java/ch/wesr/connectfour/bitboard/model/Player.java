package ch.wesr.connectfour.bitboard.model;

public class Player {

    private DiscType discType;
    private String name;
    private Game game;

    public Player(DiscType discType, String name) {
        this.discType = discType;
        this.name = name;
    }


    public Game startGame() {
        this.game = new Game();
        this.game.setFirstPlayer(this);
        return this.game;
    }

    public void joinGame(Game gameToBeJoined) {
        if (gameToBeJoined.getFirstPlayer() == null) {
            throw new IllegalArgumentException("Can not join game - start the game first");
        }
        if (gameToBeJoined.getFirstPlayer().getDiscType().equals(this.getDiscType())) {
            throw new IllegalArgumentException("2 player with identical disc type [ " + this.getDiscType() + " ]");
        }

        this.game = gameToBeJoined;
        this.game.printGame();
    }

    public long makeMove(int column)  {
        if (this.game.getCurrentPlayer() != null && this.game.getCurrentPlayer().equals(this)) {
            throw new IllegalArgumentException(this.getName() + " is not the next player");
        }
        this.game.setCurrentPlayer(this);
        long move = this.game.makeMove(this.discType, column);

        return move;
    }

    public long undoMove(int column) {
        return this.game.undoMove(this.discType, column);
    }

    public void printGame() {
        game.printGame();
    }

    public DiscType getDiscType() {
        return discType;
    }

    public void setDiscType(DiscType discType) {
        this.discType = discType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMostRecentlyMove() {
        return this.game.getMostRecentlyMove(this.discType);
    }
}
