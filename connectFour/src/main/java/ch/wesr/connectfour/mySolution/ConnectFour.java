package ch.wesr.connectfour.mySolution;

import ch.wesr.connectfour.mySolution.model.DiscType;
import ch.wesr.connectfour.mySolution.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectFour {

    private static Player playerX;
    private static Player playerO;

    public static void main(String[] args) throws IOException {
        System.out.println("Connect four / View gewinnt!\n\n");
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean gewonnen=false;

        System.out.print("Which player begins: X or O > ");
        line=in.readLine();
        if (line.equals(DiscType.X.toString())) {
            System.out.print("X beginnt, gib dein Name ein: > ");
            String playerXname = in.readLine();
            System.out.print("Hallo "+playerXname +", gib den Namen deines Spielpartner ein > ");
            playerX = new Player(playerXname, DiscType.X);
            playerX.startGame(7,7);
        } else if (line.equals(DiscType.O.toString())) {
            System.out.print("O beginnt, gib dein Name ein: > ");
            String playerOname = in.readLine();
            System.out.print("Hallo "+playerOname +", gib den Namen deines Spielpartner ein > " );
            playerO = new Player(playerOname, DiscType.X);
            playerX.startGame(7,7);
        } else {
            System.out.println("Da ging wohl etwas schief - ciao");
            System.exit(13);
        }

        if (playerX == null) {
            String playerXname = in.readLine();
            System.out.println("Hallo " +playerXname +" du spielst mit (" +DiscType.O.toString() +")" );
            playerX = new Player(playerXname, DiscType.X);
            playerX.join(playerO);
        } else {
            String playerOname = in.readLine();
            System.out.println("Hallo "+playerOname +" du spielst mit (" +DiscType.O.toString() +")" );
            playerO = new Player(playerOname, DiscType.O);
            playerO.join(playerX);
        }

        Player nextPlayer = playerO.isGameStartedByMe() ? playerO : playerX;
        System.out.println("Naechster Zug durch "+nextPlayer.getName());
        playerO.printGameTable();
        System.out.print("Gib die Koordinaten ein: > ");
        line=in.readLine();

        while(!gewonnen && line!=null && line.length()>0) {
            // TODO absichern
            String[] split = line.split(",");
            int y = Integer.parseInt(split[0]);
            int x = Integer.parseInt(split[1]);

            gewonnen = nextPlayer.dropStone(y, x);
            if (gewonnen) {
                System.out.println("!!!!! " +nextPlayer.getName() + " gewinnt das Spiel !!!!!");
                nextPlayer.printGameTable();
                System.exit(0);
            }
            nextPlayer.printGameTable();
            nextPlayer = nextPlayer.isNextPlayer() ? nextPlayer : nextPlayer.getJoinedPlayer();
            System.out.println("Naechster Zug durch "+nextPlayer.getName());
            System.out.print("Gib die Koordinaten ein: > ");
            line = in.readLine();
        }
    }
}
