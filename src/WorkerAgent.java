public class WorkerAgent {
    private int idWorker;
    private WorkerState state;
    private int currentTile;
    private int goalTile; //-1 = no goal
    private IWorkerActions actionsUtil;

    public WorkerAgent(int idWorker, int currentTile) {
        this.state = WorkerState.Idle;
        this.currentTile = currentTile;
        this.goalTile = -1;             // -1 = pas de but
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
