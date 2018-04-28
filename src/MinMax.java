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


    private TileHeuristic MaxValue(HashMap<WorkerAgent,ArrayList<TileHeuristic>> remainingMap,int depthExplo)
    {
        if(depthExplo == this.depth)
            return new TileHeuristic(0,0);
        TileHeuristic value = new TileHeuristic(0, -2000);
        /*
        recuperer le premier agent de la liste envoyé en parametre et son array list de tuiles
        ensuite regarder toutes les tuiles associées et récuperer la tuile avec l'index le plus haut
         */
        ArrayList<TileHeuristic> actions =  remainingMap.remove(agents.remove(0));
        if(true) // regarder si c'est notre agent ou celui de l'adversaire pour prévoir les coups adverses

        return new TileHeuristic(0,0);
    }

    private TileHeuristic MinValue(HashMap<WorkerAgent,ArrayList<TileHeuristic>> remainingMap,int depthExplo)
    {
        return new TileHeuristic(1,1);
    }

    private int MinMaxDecision(WorkerAgent w)
    {
        return new TileHeuristic(0,0);
    }

    private HashMap<WorkerAgent,Integer> finalDecision()
    {

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
