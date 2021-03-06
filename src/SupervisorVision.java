import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
/*
Classe permettant au superviseur d'observer la carte a chaque changement

 */
public class SupervisorVision implements Observer {

    private ArrayList<Tile> tiles;

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public void update(Observable o, Object arg) {
        Map map;
        if(arg instanceof Map) {
            map = (Map) arg;
            this.tiles = map.getTiles();
        }
        else {
            System.err.println("Illegal argument in SupervisorVision.update function");
        }
    }

    public Tile getTileById(int idN) {
        for(Tile t : tiles) {
            if(t.getIdTile() == idN)
                return t;
        }

        return null;
    }
}
