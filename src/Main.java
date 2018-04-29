public class Main {
    public static void main (String args[]) {
        String mapPath = "xmlFiles\\map.xml";
        String playerPath = "xmlFiles\\players.xml";
        String workerPath = "xmlFiles\\workers.xml";

        Game game = new Game();
        game.setupGame(mapPath, playerPath, workerPath);
        //game.start();
    }
}
