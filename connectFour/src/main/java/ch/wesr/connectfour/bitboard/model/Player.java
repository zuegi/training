package ch.wesr.connectfour.bitboard.model;

import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.IllegalUndoMoveException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import lombok.SneakyThrows;

public class Player {

    private DiscType discType;
    private String name;
    private Game game;
    private boolean maximizer;


    public Player(DiscType discType, String name) {
        this.discType = discType;
        this.name = name;
    }


    public Game startGame() {
        this.game = new Game();
        this.game.setFirstPlayer(this);
        this.maximizer = true;
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
    }

    @SneakyThrows
    public boolean makeMove(int column) {
        if (this.game.getCurrentPlayer() != null && this.game.getCurrentPlayer().equals(this)) {
            throw new IllegalArgumentException(this.getName() + " is not the next player");
        }
        if (!game.isPossibleColum(column)) {
            throw new OutsideOfGameBoardException(this.name +" plays outside of the board: " +column);
        }
        this.game.setCurrentPlayer(this);
        boolean isValidMove = game.makeMove(this.discType, column);
        if (game.isWinner(discType)) {
            throw new GameOverException(this.name +" won the game");
        }
        game.printBoard();
        return isValidMove;
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

    @SneakyThrows
    public boolean undoMove(int column) {
        if (this.game.getCurrentPlayer() != null && !this.game.getCurrentPlayer().equals(this)) {
            throw new IllegalUndoMoveException(this.getName() + " is not the next player");
        }
//        if (game.isPossibleColum(column)) {
//            game.setCurrentPlayer(this);
//            return game.undoMove(this.discType, column);
//        }
        return false;
    }

    public boolean makeBestMove() {
        int bestColumn = game.findBestColumn(this.discType);
        game.setCurrentPlayer(this);
        boolean validMove = game.makeMove(this.discType, bestColumn);
        game.printBoard();
        return validMove;
    }

//    public long getMostRecentlyMove() {
//        return this.game.getMostRecentlyMove(this.discType);
//    }
//
//    public boolean isMaximizer() {
//        return maximizer;
//    }
//
//    public void setMaximizer(boolean maximizer) {
//        this.maximizer = maximizer;
//    }
}
