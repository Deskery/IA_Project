/*
CLasse définissant l'ouvrier
 */


public class WorkerAgent {
    private int idWorker;
    private WorkerState state;
    private Tile currentTile;
    private Tile goalTile; //null = no goal

    public WorkerAgent(int idWorker, Tile currentTile) {
        this.idWorker = idWorker;
        this.state = WorkerState.Idle;
        this.currentTile = currentTile;
        this.goalTile = null;             // -1 = pas de but
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public WorkerState getState() {
        return state;
    }

    public void setState(WorkerState state) {
        this.state = state;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Tile getGoalTile() {
        return goalTile;
    }

    public void setGoalTile(Tile goalTile) {
        this.goalTile = goalTile;
    }

}
