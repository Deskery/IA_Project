import java.util.ArrayList;
import java.util.HashMap;

public class MinMax {
    private ArrayList<WorkerAgent> agents;
    private HashMap<WorkerAgent,ArrayList<TileHeuristic>> problem;
    private HashMap<WorkerAgent,Integer> solution;
    private int depth;
    public MinMax(HashMap<WorkerAgent, ArrayList<TileHeuristic>> problem, ArrayList<WorkerAgent> agents)
    {
        this.agents = agents;
        this.problem = problem;
        this.solution = new HashMap<WorkerAgent, Integer>();
        this.depth = problem.size();
    }

    private TileHeuristic Max(TileHeuristic t1 , TileHeuristic t2)
    {
        if(t1.getHeuristicValue() < t2.getHeuristicValue())
        {
            return t2;
        }
        else
            return t1;
    }

    private TileHeuristic Min(TileHeuristic t1 , TileHeuristic t2)
    {
        if(t1.getHeuristicValue() > t2.getHeuristicValue())
        {
            return t2;
        }
        else
            return t1;
    }

    private TileHeuristic MinMaxValue(HashMap<WorkerAgent,ArrayList<TileHeuristic>> remainingMap, ArrayList<WorkerAgent> workerAgents)
    {
        if(remainingMap.size() == 0)
            return new TileHeuristic(0,0);
        TileHeuristic value = new TileHeuristic(0, - 2000);
        /*
        recuperer le premier agent de la liste envoyé en parametre et son array list de tuiles
        ensuite regarder toutes les tuiles associées et récuperer la tuile avec l'index le plus haut
         */
        ArrayList<TileHeuristic> actions =  remainingMap.remove(workerAgents.remove(0));
        if(true) // regarder si c'est notre agent ou celui de l'adversaire pour prévoir les coups adverses
        {
            for (TileHeuristic tileH : actions)
            {
                value = Max(value,tileH);
            }
        }
        else // si jamais agent ennemi cette partie du code n'est pas fonctionnelle et sert de template pour montrer a quoi ressemblerait l'algorithme si l'on prenait en compte les adversaires
        {
            for (TileHeuristic tileH : actions)
            {
                HashMap<WorkerAgent,ArrayList<TileHeuristic>> newMap = remainingMap;
                for (WorkerAgent w : workerAgents)
                {
                    ArrayList<TileHeuristic> workerTiles = newMap.remove(w);
                    workerTiles.remove(tileH);
                    newMap.putIfAbsent(w, workerTiles);
                }
                value = Min(value,MinMaxValue(newMap,workerAgents));
            }
        }
        return new TileHeuristic(0,0);
    }


    public HashMap<WorkerAgent,Integer> finalDecision()
    {


        ArrayList<WorkerAgent> workers = agents;
        while(workers.size() != 0)
        {
            TileHeuristic tile = MinMaxValue(problem, workers);
            WorkerAgent worker = workers.remove(0);
            solution.putIfAbsent(worker, tile.getTileId());

            for (WorkerAgent w : workers)
            {
                ArrayList<TileHeuristic> workerTiles = problem.remove(w);
                workerTiles.remove(tile);
                problem.putIfAbsent(w, workerTiles);
            }
        }
        //travailler a meme la liste d'agent et les retirer au fur et a mesure que l'on crée la solution
        return solution;
    }
    /*
    public Tile chooseFringeNode(ArrayList<Tile> fringe)
    {

        return fringe.remove();
    }

    public ArrayList<Node> expand(Problem problem, Node node) {
        LinkedList<Node> successors = new LinkedList<>();
        for(Action action: problem.successors(node.getState()))
        {

            String endState = action.getEndState();
            if(!node.hasParent(endState))
            {

                int cost = problem.getCost(node.getState(), endState);
                Node s = new Node(endState, node, node.getDepth() + 1, cost);
                s.setHeurCost(problem.getStrait(endState) + s.solutionCost());
                successors.add(s);
            }

        }

        return successors;
    }

    public LinkedList<Node> insertAll(LinkedList<Node> expand, LinkedList<Node> fringe) // depth first search
    {

        expand.addAll(fringe);
        Collections.sort(expand);

        return expand;
    }
    */
}
