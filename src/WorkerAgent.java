public class WorkerAgent {
    private WorkerState state;
    private int currentTile;
    private int goalTile; //-1 = no goal
    private IWorkerActions actionsUtil;

    public WorkerAgent(WorkerState state, int currentTile, int goalTile) {
        this.state = state;
        this.currentTile = currentTile;
        this.goalTile = goalTile;
        this.actionsUtil = new WorkerAction();
    }

    public WorkerState getState() {
        return state;
    }

    public void setState(WorkerState state) {
        this.state = state;
    }

    public int getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(int currentTile) {
        this.currentTile = currentTile;
    }

    public int getGoalTile() {
        return goalTile;
    }

    public void setGoalTile(int goalTile) {
        this.goalTile = goalTile;
    }

}
