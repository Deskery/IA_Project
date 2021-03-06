import java.util.ArrayList;
import java.util.HashMap;

/*
CLasse implémentant l'algorithme MinMax transformé
 */


public class MinMax {
    private ArrayList<WorkerAgent> agents;
    private HashMap<WorkerAgent,ArrayList<TileHeuristic>> problem;
    private HashMap<WorkerAgent,Integer> solution;
    public MinMax(HashMap<WorkerAgent, ArrayList<TileHeuristic>> problem, ArrayList<WorkerAgent> agents)
    {
        this.agents = agents;
        this.problem = problem;
        this.solution = new HashMap<>();
    }

    // Renvoie la tuile dont l'heuristique est la plus grande
    private TileHeuristic Max(TileHeuristic t1 , TileHeuristic t2)
    {

        if(t1.getHeuristicValue() < t2.getHeuristicValue())
        {

            return t2;
        }
        else
            return t1;
    }
    // Renvoie la tuile dont l'heuristique est la plus petite
    private TileHeuristic Min(TileHeuristic t1 , TileHeuristic t2)
    {
        if(t1.getHeuristicValue() > t2.getHeuristicValue())
        {
            return t2;
        }
        else
            return t1;
    }

    //Renvoie la tuile heuristique menant a l'arbre le plus prometteur
    private TileHeuristic MinMaxValue(HashMap<WorkerAgent,ArrayList<TileHeuristic>> remainingMap, ArrayList<WorkerAgent> workerAgents,TileHeuristic util)
    {
        ArrayList<WorkerAgent> agentList = new ArrayList<>(workerAgents);
        if(remainingMap.size() == 0) // On est au bout de l'arbre on renvoie la tuile qui y est
            return util;
        TileHeuristic value = new TileHeuristic(-1, -2000);

        //retire l'agent en cours de la liste a explorer et etends l'arbre avec les tuiles de cet agent
        HashMap<WorkerAgent,ArrayList<TileHeuristic>> newMap = new HashMap<>(remainingMap);
        ArrayList<TileHeuristic> actions =  new ArrayList<>(newMap.remove(agentList.remove(0)));

            for (TileHeuristic tileH : actions)
            {

                for (WorkerAgent w : agentList)
                {
                    ArrayList<TileHeuristic> workerTiles = new ArrayList<>(newMap.remove(w));
                    workerTiles.remove(tileH);
                    newMap.putIfAbsent(w, workerTiles);
                }

                TileHeuristic minManTile = MinMaxValue(newMap, agentList, tileH);

                if(true) // regarder si c'est notre agent ou celui de l'adversaire pour prévoir les coups adverses
                    // La liste d'agent serait alors une liste d'entité et il y aurait besoin de foncions d'heuristiques pour chque entité
                    value = Max(value, minManTile);
                else
                    value = Min(value,minManTile);

                if(minManTile.getHeuristicValue() == value.getHeuristicValue())
                {
                    if(util == null || tileH.getHeuristicValue() > util.getHeuristicValue() )
                        util = tileH;
                }

            }

        return util; // renvoie la tuile a aller chercher par l'agent
    }

    //fonction supérieur de l'algorithme attribuant une case a chaque agent et renvoyant l'attribution
    public HashMap<WorkerAgent,Integer> finalDecision()
    {


        ArrayList<WorkerAgent> workers = new ArrayList<>(agents);
        while(workers.size() != 0)
        {
            TileHeuristic tile = MinMaxValue(problem, workers,null);
            WorkerAgent worker = workers.remove(0);
            solution.putIfAbsent(worker, tile.getTileId());

            for (WorkerAgent w : workers)
            {
                ArrayList<TileHeuristic> workerTiles = problem.remove(w);
                TileHeuristic temp = new TileHeuristic(0,0) ;
                for(TileHeuristic t :workerTiles)
                {
                    if(t.getTileId() == tile.getTileId())
                        temp= t;
                }
                workerTiles.remove(temp);
                problem.putIfAbsent(w, workerTiles);
            }
            problem.remove(worker);
        }
        return solution;
    }

}
