package ch.wesr.connectfour.bitboard.model;

public class Game {

    private final BitBoard bitBoard;
    private PrintGame printBoard;
    private Player firstPlayer;
    private Player currentPlayer;
    private boolean printMoves;

    public Game() {
        this.bitBoard = new BitBoard();
        this.printBoard = new PrintGame();
    }



    public boolean makeMove(DiscType discType, int column) {
        boolean validMove = false;
        if (bitBoard.isPossibleColumn(column)) {
            validMove= bitBoard.makeMove(discType, column);
        }
        return validMove;
    }

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

    public boolean isWinner(DiscType discType) {
        return bitBoard.isWinner(discType);
    }

    public boolean isPossibleColum(int column) {
        return bitBoard.isPossibleColumn(column);
    }

    public boolean undoMove(DiscType discType, int column) {
        return bitBoard.undoMove(discType, column);
    }


    public int findBestColumn(DiscType discType) {
        return bitBoard.findBestColumn(discType);
    }

    public void printBoard() {
        if(printMoves)
            printBoard.printBoard(bitBoard);
    }
}
