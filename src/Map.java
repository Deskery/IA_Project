import java.util.ArrayList;

public class Map {
    private ArrayList<Case> cases;

    public Map() {
        this.cases = new ArrayList<>();
    }

    public ArrayList<Case> getCases() {
        return cases;
    }

    public void setCases(ArrayList<Case> cases) {
        this.cases = cases;
    }

    public void addCase(Case c) {
        cases.add(c);
    }

    public void addCase(Location location, EnvironmentType et, EnvironmentCharacteristics ec, Resource resource, boolean amenagement) {
        cases.add(new Case(location, et, ec, resource, amenagement));
    }
}
