import java.util.ArrayList;

public class Main {
    public static void main (String args[]) {
        String mapPath = "xmlFiles\\map.xml";
        String PlayerPath = "xmlFiles\\players.xml";
        Game game = setupGame(mapPath, mapPath);
    }

    private static Game setupGame(String mapPath, String PlayerPath) {
        Map map = XMLReader.generateMap(mapPath);
        ArrayList<Player> players = XMLReader.generatePlayers(PlayerPath);
        return new Game(map, players);
    }


}
