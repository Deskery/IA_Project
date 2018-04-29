import java.util.ArrayList;
import java.util.Observable;

public class Map extends Observable{
    private ArrayList<Tile> tiles;

    public Map() {
        this.tiles = new ArrayList<>();
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public void addCase(Tile c) {
        tiles.add(c);
    }

    public void addCase(int id, EnvironmentType et, EnvironmentCharacteristics ec, Resource resource, boolean amenagement) {
        tiles.add(new Tile(id, et, ec, resource, amenagement));
    }

    public void nextIteration() {
        setChanged();
        notifyObservers(this);
    }

    public Tile getTileById(int idN) {
        for(Tile t : tiles) {
            if(t.getIdTile() == idN) {
                return t;
            }

        }

        return null;
    }
}
