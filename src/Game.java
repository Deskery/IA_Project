import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<Player> players;
    private int nbRound;

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
}
