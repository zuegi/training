package ch.wesr.connectfour.bitboard.model;

import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import lombok.SneakyThrows;

import java.util.List;

public class Game {

    private final BitBoard bitBoard;
    private Player firstPlayer;
    private boolean printMoves;
    private Player currentPlayer;
    private PrintGame printBoard;

    public Game() {
        this.bitBoard = new BitBoard();
        this.printBoard = new PrintGame();
    }

    @SneakyThrows
    public long makeMove(DiscType discType, int column) {
        if (!isPossibleMove(column)) {
            throw new OutsideOfGameBoardException(this.currentPlayer.getName() + " plays outside of the board");
        }
        long bitboard = bitBoard.makeMove(discType, column);
        checkWinner(bitboard);
        return bitboard;
    }

    @SneakyThrows
    public long undoMove(DiscType discType, int column) {
        if (!isPossibleMove(column)) {
            throw new OutsideOfGameBoardException(this.currentPlayer.getName() + " plays outside of the board");
        }
        long bitboard = bitBoard.undoMove(discType, column);
        return bitboard;
    }

    private boolean isPossibleMove(int column) {
        long count = bitBoard.listColumnsOfPossibleMoves().stream().filter(integer -> integer.equals(column)).count();
        return count > 0;
    }

    public int[] listColumnsOfPossibleMoves() {
        List<Integer> integers = bitBoard.listColumnsOfPossibleMoves();
       return integers.stream().mapToInt(i->i).toArray();
    }

    public void printGame() {
        if (printMoves) {
            printBoard.printBoard(bitBoard.getBitboardMap());
        }
    }

    public void setFirstPlayer(Player player) {
        this.firstPlayer = player;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void printMoves(boolean printMoves) {
        this.printMoves = printMoves;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void checkWinner(long bitboard) {
        if (printMoves) {
            this.printGame();
        }

        long diagonalTopLeft = bitboard & (bitboard >> 6) & (bitboard >> 12) & (bitboard >> 18);
        long diagonalDownLeft = bitboard & (bitboard >> 8) & (bitboard >> 16) & (bitboard >> 24);
        long horizontal = bitboard & (bitboard >> 7) & (bitboard >> 14) & (bitboard >> 21);
        long vertical = bitboard & (bitboard >> 1) & (bitboard >> 2) & (bitboard >> 3);

        if (diagonalTopLeft != 0) {
            throw new GameOverException("Player " + currentPlayer.getName() + " won diagonal top left to down right");
        } else if (diagonalDownLeft != 0) {
            throw new GameOverException("Player " + currentPlayer.getName() + " won diagonal down left to top right");
        } else if (horizontal != 0) {
            throw new GameOverException("Player " + currentPlayer.getName() + " won horizontal");
        } else if (vertical != 0) {
            throw new GameOverException("Player " + currentPlayer.getName() + " won vertical");
        }
    }

    public long getMostRecentlyMove(DiscType discType) {
        return bitBoard.getMostRecentlyMove(discType);
    }


    public int findBestMove(/*DiscType discType,*/ boolean maximizer) {
//        return bitBoard.findBestMove(/*discType,*/ maximizer);
        return 0;
    }
}
