import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Supervisor {
    private ArrayList<WorkerAgent> workers;
    private SupervisorVision vision;
    private float[][] distanceMatrix;
    private HashMap<WorkerAgent,Integer> finalList ;
    private int suppTiles;
    public Supervisor(ArrayList<WorkerAgent> workers, float[][] distanceMatrix) {
        this.vision = new SupervisorVision();
        this.distanceMatrix = distanceMatrix;
        this.workers = workers;
        this.suppTiles = 2;
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

    public void generateList()
    {
        ArrayList<Tile> tiles = vision.getTiles();
        ArrayList<TileHeuristic> heuristicTiles = new ArrayList<TileHeuristic>();
        for( Tile t : tiles)
        {
            heuristicTiles.add(new TileHeuristic(1,HeuristicUtil.CheckTileHeuristic(t)));
        }
        Collections.sort(heuristicTiles);
        int nbAgent = workers.size() + suppTiles;
        reduceTileListe(nbAgent,heuristicTiles);


        HashMap<WorkerAgent,ArrayList<TileHeuristic>> workerHeuristicList = new HashMap<WorkerAgent,ArrayList<TileHeuristic>>();

        for(WorkerAgent w : this.workers)
        {
            ArrayList<TileHeuristic> wTiles = new ArrayList<>(heuristicTiles);
            HeuristicUtil.WorkerHeuristic(this.distanceMatrix,w.getCurrentTile(),wTiles);
            workerHeuristicList.putIfAbsent(w,wTiles);
        }

        MinMax search = new MinMax(workerHeuristicList,this.workers);
        finalList = search.finalDecision();
    }

    private ArrayList<TileHeuristic> reduceTileListe (int size,ArrayList<TileHeuristic> oldList)
    {
        ArrayList<TileHeuristic> newList = new ArrayList<>();
        for(int i = 0; i<size; i++)
        {
            newList.add(oldList.remove(0));
        }
        return  newList;
    }
}
