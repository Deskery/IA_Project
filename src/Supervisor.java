import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Supervisor {
    private ArrayList<WorkerAgent> workers;
    private SupervisorVision vision;
    private double[][] distanceMatrix;
    private HashMap<WorkerAgent,Integer> finalList ;
    private int suppTiles;
    private WorkerAction action;

    public Supervisor(ArrayList<WorkerAgent> workers, double[][] distanceMatrix) {
        this.vision = new SupervisorVision();
        this.distanceMatrix = distanceMatrix;
        this.workers = workers;
        this.suppTiles = 2;
        this.action = new WorkerAction();
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

    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(double[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public void generateList()
    {
        ArrayList<Tile> tiles = vision.getTiles();
        ArrayList<TileHeuristic> heuristicTiles = new ArrayList<>();
        for( Tile t : tiles)
        {
            heuristicTiles.add(new TileHeuristic(t.getIdTile(),HeuristicUtil.CheckTileHeuristic(t)));
        }
        Collections.sort(heuristicTiles);
        int nbAgent = workers.size() + suppTiles;
        heuristicTiles = reduceTileList(nbAgent,heuristicTiles);


        HashMap<WorkerAgent,ArrayList<TileHeuristic>> workerHeuristicList = new HashMap<WorkerAgent,ArrayList<TileHeuristic>>();

        for(WorkerAgent w : this.workers)
        {
            ArrayList<TileHeuristic> wTiles = new ArrayList<>(heuristicTiles);
            HeuristicUtil.WorkerHeuristic(this.distanceMatrix,w.getCurrentTile().getIdTile(),wTiles);
            workerHeuristicList.putIfAbsent(w,wTiles);
        }

        MinMax search = new MinMax(workerHeuristicList, this.workers);
        finalList = search.finalDecision();
        this.giveOrders(tiles);
    }

    private ArrayList<TileHeuristic> reduceTileList (int size,ArrayList<TileHeuristic> oldList)
    {
        ArrayList<TileHeuristic> newList = new ArrayList<>();
        for(int i = 0; i<size; i++)
        {
            newList.add(oldList.remove(0));
        }
        return  newList;
    }

    private void giveOrders(ArrayList<Tile> tiles)
    {
        ArrayList<WorkerAgent> workers = new ArrayList<>(this.workers);
        while (!finalList.isEmpty())
        {
            WorkerAgent w = workers.remove(0);
            int tileId = finalList.remove(w);
            w.setGoalTile(vision.getTileById(tileId));
            boolean isBuilt= false;
            for (Tile t: tiles)
            {
                if(t.getIdTile()==tileId && t.isAmenagement())
                    isBuilt = true;
            }

            if(isBuilt)
            {
                w.setState(WorkerState.Idle);
                System.out.println("L'agent " + w.getIdWorker() +" n'a pas trouvé de case interessante il se met au repos");
            }
            else if(w.getCurrentTile().getIdTile() != tileId)
            {
                w.setState(WorkerState.Moving);
                System.out.println("L'agent " + w.getIdWorker() +" doit aller construire a la tuile " +tileId+ " il est a " +w.getCurrentTile().getIdTile());
            }
            else
            {
                w.setState(WorkerState.Building);
                System.out.println("L'agent " + w.getIdWorker() +" est sur la tuile " +tileId+ " il doit construire sur " +tileId);
            }
        }
    }

    public void executeOrder(){
        for(WorkerAgent w : this.workers) {

            if(w.getState().equals(WorkerState.Building)) {
                action.buildAmenagment(w.getCurrentTile());
            }

            if(w.getState().equals(WorkerState.Moving)) {

                Tile currentTile = w.getCurrentTile();
                Tile destinationTile = w.getGoalTile();

                if(distanceMatrix[currentTile.getIdTile()][destinationTile.getIdTile()] <= 1) {
                    action.moveTo(w, destinationTile);
                }
                else {
                    Tile nextTile = findNextTile(currentTile, destinationTile);
                    action.moveTo(w, nextTile);
                }
            }
        }
    }

    private Tile findNextTile(Tile currentTile, Tile destinationTile) {
        Tile nextTile = null;
        double shortestDistance = Double.POSITIVE_INFINITY;
        double tempDistance;

        for(Tile neighbour : currentTile.getNeighbours()) {
            tempDistance = distanceMatrix[neighbour.getIdTile()][destinationTile.getIdTile()];
            if(tempDistance < shortestDistance) {
                shortestDistance = tempDistance;
                nextTile = neighbour;
            }
        }

        return nextTile;
    }
}
