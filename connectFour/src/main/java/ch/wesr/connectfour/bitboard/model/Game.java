package ch.wesr.connectfour.bitboard.model;

import ch.wesr.connectfour.bitboard.model.exception.GameOverException;
import ch.wesr.connectfour.bitboard.model.exception.OutsideOfGameBoardException;
import ch.wesr.connectfour.mySolution.model.Disc;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.IntStream;

public class Game {

//    private final BitBoard bitBoard;
//    private Player firstPlayer;
//    private boolean printMoves;
//    private Player currentPlayer;
//    private PrintGame printBoard;
//
//    public Game() {
//        this.bitBoard = new BitBoard();
//        this.printBoard = new PrintGame();
//    }
//
//
//    @SneakyThrows
//    public long makeMove(DiscType discType, int column) {
//        if (!isPossibleMove(column)) {
//            throw new OutsideOfGameBoardException(this.currentPlayer.getName() + " plays outside of the board");
//        }
//        long bitboard = bitBoard.makeMove(discType, column);
//        checkWinner(bitboard);
//        return bitboard;
//    }
//
//    @SneakyThrows
//    public long undoMove(DiscType discType, int column) {
//        if (!isPossibleMove(column)) {
//            throw new OutsideOfGameBoardException(this.currentPlayer.getName() + " plays outside of the board");
//        }
//        long bitboard = bitBoard.undoMove(discType);
//        return bitboard;
//    }
//
//    private boolean isPossibleMove(int column) {
//        int[] possibleMoves = bitBoard.listColumnsOfPossibleMoves();
//        boolean contains = IntStream.of(possibleMoves).anyMatch(x -> x == column);
//        long count = possibleMoves.length;
//        return contains && column >= 0 && column <= BitBoard.BOARD_WIDTH && count > 0;
//    }
//
//    private void checkWinner(long bitboard) {
//        if (printMoves) {
//            this.printGame();
//        }
//    }
//
//    public void printGame() {
//        if (printMoves) {
//            printBoard.printBoard(bitBoard.getBitboardMap());
//        }
//    }
//
//    public void setFirstPlayer(Player player) {
//        this.firstPlayer = player;
//    }
//
//    public Player getFirstPlayer() {
//        return firstPlayer;
//    }
//
//    public void printMoves(boolean printMoves) {
//        this.printMoves = printMoves;
//    }
//
//    public void setCurrentPlayer(Player player) {
//        this.currentPlayer = player;
//    }
//
//    public Player getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public long getMostRecentlyMove(DiscType discType) {
//        return bitBoard.getMostRecentlyMove(discType);
//    }

}
