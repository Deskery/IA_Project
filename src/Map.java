import java.util.ArrayList;

public class Map {
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

    public void addCase(Location location, EnvironmentType et, EnvironmentCharacteristics ec, Resource resource, boolean amenagement) {
        tiles.add(new Tile(location, et, ec, resource, amenagement));
    }
}
