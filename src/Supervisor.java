import java.util.ArrayList;

public class Supervisor {
    private ArrayList<WorkerAgent> workers;
    private SupervisorVision vision;
    private float[][] distanceMatrix;

    public Supervisor(ArrayList<WorkerAgent> workers, float[][] distanceMatrix) {
        this.vision = new SupervisorVision();
        this.distanceMatrix = distanceMatrix;
        this.workers = workers;
    }

    public ArrayList<WorkerAgent> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<WorkerAgent> workers) {
        this.workers = workers;
    }

    public SupervisorVision getVision() {
        return vision;
    }

    public void setVision(SupervisorVision vision) {
        this.vision = vision;
    }

    public float[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(float[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }
}
