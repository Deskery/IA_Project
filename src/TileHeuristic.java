import java.util.Collections;
public class TileHeuristic implements Comparable{
    private int tileId;
    private int heuristicValue;

    public TileHeuristic(int tileId, int heuristicValue) {

        this.tileId = tileId;
        this.heuristicValue = heuristicValue;
    }


    @Override
    public int compareTo(Object o) {
        TileHeuristic a = (TileHeuristic)o;
        if(a.heuristicValue < this.heuristicValue)
           return 1;
        else if(a.heuristicValue>this.heuristicValue)
            return -1;
        else
            return 0;
    }
}
