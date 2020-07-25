package ch.wesr.connectfour;

import ch.wesr.connectfour.model.Disc;
import ch.wesr.connectfour.model.DiscType;
import ch.wesr.connectfour.model.Game;
import ch.wesr.connectfour.model.Player;

public class ConnectFour {

    public static void main(String[] args) {
        Player player1 = new Player("bot1", DiscType.X);
        player1.startGame(7,7);
        Player player2 = new Player("bot2", DiscType.O);
        player2.join(player1);
        player1.dropStone(0, 3);
        player1.printGameTable();
        player2.dropStone(0, 4);
        player2.printGameTable();
    }
}
