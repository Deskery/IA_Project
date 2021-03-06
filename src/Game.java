import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/*
Classe gérant l'univers de jeu
Initiliase la map les joueurs et les workers
Comprend également la boucle de jeu et les commandes a executer
 */


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

        for(Player p : players) {
            p.getWorkerSupervisor().generateList();     // Calcule les buts et les attribut aux workers
            p.getWorkerSupervisor().executeOrder();     // Execute les ordres déterminés à l'étape précédante
        }
    }

    public void setupGame(String mapPath, String playerPath, String workerPath) {
        this.map = XMLReader.generateMap(mapPath);
        FloydWarshall fwAlgorithm = new FloydWarshall(this.map.getTiles().size());

        for (Tile start : this.map.getTiles()) {
            for(Tile end: start.getNeighbours()) {
                fwAlgorithm.addEdge(start.getIdTile(), end.getIdTile(), 1);
            }
        }

        double distanceMatrix[][] = fwAlgorithm.floydWarshall();

        this.players = XMLReader.generatePlayers(playerPath);

        for (Player player : this.players) {
            ArrayList<WorkerAgent> workers = XMLReader.generateWorker(player.getIdPlayer(), workerPath, this.map);

            Supervisor supervisor = new Supervisor(workers, distanceMatrix);
            player.setWorkerSupervisor(supervisor);

            // On ajoute le senseur vision du superviseur en temps qu'observeur de la map
            map.addObserver(player.getWorkerSupervisor().getVision());
        }
    }

    public void start(){
        while (nbRound < 250) {
            System.out.println("Round : " + nbRound);
            nextIteration();
            nbRound++;

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
