public class Tile {
    private Location location;
    private EnvironmentType environmentType;
    private EnvironmentCharacteristics environmentCharacteristics;
    private Resource resource;
    private boolean amenagement;

    public Tile(Location location, EnvironmentType environmentType, EnvironmentCharacteristics environmentCharacteristics, Resource resource, boolean amenagement) {
        this.location = location;
        this.environmentType = environmentType;
        this.environmentCharacteristics = environmentCharacteristics;
        this.resource = resource;
        this.amenagement = amenagement;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
}
