import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<Player> players;
    private int nbRound;

    public Game() {
        nbRound = 0;
    }

    public Game(Map map, ArrayList<Player> players) {
        this.map = map;
        this.players = players;
        nbRound = 0;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getNbRound() {
        return nbRound;
    }

    public void setNbRound(int nbRound) {
        this.nbRound = nbRound;
    }

    public void nextIteration() {
        map.nextIteration();
    }

    public void setupGame(String mapPath, String playerPath, String workerPath) {
        this.map = XMLReader.generateMap(mapPath);
        float distanceMatrix[][] = AStar.generateDistanceMatrix(map.getTiles());

        this.players = XMLReader.generatePlayers(playerPath);

        for (Player player : this.players) {
            ArrayList<WorkerAgent> workers = XMLReader.generateWorker(player.getIdPlayer(), workerPath);
            Supervisor supervisor = new Supervisor(workers, distanceMatrix);
            player.setWorkerSupervisor(supervisor);

            // On ajoute le senseur vision du superviseur en temps qu'observeur de la map
            map.addObserver(player.getWorkerSupervisor().getVision());
        }
    }
}
