/*
Classe representant une tuile mais servant dans le calcul d'heuristique objet plus simple a manipuler dans les
algorithme et plus leger
 */



public class TileHeuristic implements Comparable{
    private int tileId;
    private int heuristicValue;

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public TileHeuristic(int tileId, int heuristicValue) {

        this.tileId = tileId;
        this.heuristicValue = heuristicValue;
    }

    public int getTileId() {
        return tileId;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    @Override
    public int compareTo(Object o) {
        TileHeuristic a = (TileHeuristic)o;
        if(a.heuristicValue < this.heuristicValue)
           return -1;
        else if(a.heuristicValue>this.heuristicValue)
            return 1;
        else
            return 0;
    }
}
