import java.util.ArrayList;

public class Tile {
    private int idTile;
    private EnvironmentType environmentType;
    private EnvironmentCharacteristics environmentCharacteristics;
    private Resource resource;
    private boolean amenagement;
    private ArrayList<Tile> neighbours;

    public Tile(int idTile, EnvironmentType environmentType, EnvironmentCharacteristics environmentCharacteristics, Resource resource, boolean amenagement) {
        this.idTile = idTile;
        this.environmentType = environmentType;
        this.environmentCharacteristics = environmentCharacteristics;
        this.resource = resource;
        this.amenagement = amenagement;
        this.neighbours = new ArrayList<>();
    }

    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    public EnvironmentCharacteristics getEnvironmentCharacteristics() {
        return environmentCharacteristics;
    }

    public void setEnvironmentCharacteristics(EnvironmentCharacteristics environmentCharacteristics) {
        this.environmentCharacteristics = environmentCharacteristics;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public boolean isAmenagement() {
        return amenagement;
    }

    public void setAmenagement(boolean amenagement) {
        this.amenagement = amenagement;
    }

    public int getIdTile() {
        return idTile;
    }

    public void setIdTile(int idTile) {
        this.idTile = idTile;
    }

    public ArrayList<Tile> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Tile> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }
}
