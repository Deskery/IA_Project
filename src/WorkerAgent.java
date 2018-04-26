public class WorkerAgent {
    private WorkerState state;
    private Location currentLocation;
    private Location goalLocation;

    public WorkerAgent(WorkerState state, Location currentLocation, Location goalLocation) {
        this.state = state;
        this.currentLocation = currentLocation;
        this.goalLocation = goalLocation;
    }

    public WorkerState getState() {
        return state;
    }

    public void setState(WorkerState state) {
        this.state = state;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Location getGoalLocation() {
        return goalLocation;
    }

    public void setGoalLocation(Location goalLocation) {
        this.goalLocation = goalLocation;
    }

    public void calculNextGoal() {

    }
}
