package ch.wesr.connectfour.bitboard.model;

public class Game {

    private final BitBoard bitBoard;
    private Player firstPlayer;
    private boolean printMoves;
    private Player currentPlayer;

    public Game() {
        this.bitBoard = new BitBoard();
    }

    public long makeMove(DiscType discType, int column)  {

        long bitboard = bitBoard.makeMove(discType, column);
        checkWinner(bitboard);
        return bitboard;
    }

    public void printGame() {
        if (printMoves) {
            bitBoard.printBoard();
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

    private void checkWinner(long bitboard) throws GameOverException {

        long diagonalTopLeft = bitboard & (bitboard >> 6) & (bitboard >> 12) & (bitboard >> 18);
        long diagonalDownLeft = bitboard & (bitboard >> 8) & (bitboard >> 16) & (bitboard >> 24);
        long horizontal = bitboard & (bitboard >> 7) & (bitboard >> 14) & (bitboard >> 21);
        long vertical = bitboard & (bitboard >> 1) & (bitboard >>  2) & (bitboard >>  3);

        if (printMoves) {
            this.printGame();
        }

        if (diagonalTopLeft != 0) {
            System.out.println("Player "+currentPlayer.getName() +" won diagonal top left to down right");
            throw new GameOverException("diagonal top left bottom right");
        } else if (diagonalDownLeft != 0) {
            System.out.println("Player "+currentPlayer.getName() +" won diagonal down left to top right");
            throw new GameOverException("diagonal bottom left top right");
        } else if (horizontal != 0) {
            System.out.println("Player "+currentPlayer.getName() +" won horizontal");
            throw new GameOverException("horizontal");
        } else if (vertical != 0) {
            System.out.println("Player "+currentPlayer.getName() +" won vertical");
            throw new GameOverException("vertical");
        }
    }

    public long getMostRecentlyMove(DiscType discType) {
        return bitBoard.getMostRecentlyMove(discType);
    }
}
