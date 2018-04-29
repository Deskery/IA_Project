import java.util.ArrayList;
import java.util.HashMap;

public class HeuristicUtil {

    public static final int foodFactor = 5;
    public static final int prodFactor = 4;
    public static final int goldFactor = 3;

    public static int CheckTileHeuristic(Tile tile)
    {
        int heurValue= 0;

        heurValue = addVars(testCharacteristics(tile.getEnvironmentCharacteristics()),testType(tile.getEnvironmentType()),testRessources(tile.getResource()));
        if(tile.isAmenagement())
            heurValue = -500;

        return heurValue;
    }


    private static int addVars(int[] t1,int [] t2, int[] t3) // on aprt du principe que cette fonction n'est utilisée que dans ce contexte
    {
        int[] heurValue = t1; // nourriture prod or possibilit de rajouté un 4 ème pouur les ressources annexe
        int finalValue = 0;
        for(int i=0;i<3;i++) {
            heurValue[i] += t2[i] + t3[i];
            switch (i) {
                case 0:
                    finalValue += heurValue[i] * foodFactor;
                    break;
                case 1:
                    finalValue += heurValue[i] * prodFactor;
                    break;
                case 2:
                    finalValue += heurValue[i] * goldFactor;
                    break;
            }
        }
        return finalValue;
    }
    private static int[] testRessources(Resource r)
    {
        int[] heurValue = new int[3]; // nourriture prod or possibilit de rajouté un 4 ème pouur les ressources annexe
        if(r == null)
        {
            heurValue[0] = 0;
            heurValue[1] = 0;
            heurValue[2] = 0;
        }
        else if (r instanceof UsefullResource )
        {
            heurValue[0] = 2;
            heurValue[1] = 4;
            heurValue[2] = 0;
        }
        else
        {
            heurValue[0] = 2;
            heurValue[1] = 2;
            heurValue[2] = 10;
        }
        return heurValue;
    }

    private static int[] testCharacteristics(EnvironmentCharacteristics carac)
    {
        int[] heurValue = new int[3]; // nourriture prod or possibilit de rajouté un 4 ème pouur les ressources annexe
        if (carac == EnvironmentCharacteristics.Desert)
        {
            heurValue[0] = 0;
            heurValue[1] = 0;
            heurValue[2] = 2;
        }
        else if(carac == EnvironmentCharacteristics.Foret)
        {
            heurValue[0] = 0;
            heurValue[1] = 2;
            heurValue[2] = 0;
        }
        else if(carac == EnvironmentCharacteristics.Prairie)
        {
            heurValue[0] = 2;
            heurValue[1] = 0;
            heurValue[2] = 0;
        }
        else
        {
            heurValue[0] = -100;
            heurValue[1] = -100;
            heurValue[2] = -100;
        }

        return heurValue;

    }
    private static int[] testType(EnvironmentType type)
    {
        int[] heurValue = new int[3]; // nourriture prod or possibilit de rajouté un 4 ème pouur les ressources annexe
        if(type == EnvironmentType.Montagne || type == EnvironmentType.Ocean || type == EnvironmentType.Glace || type == EnvironmentType.Cote || type == EnvironmentType.Lac)
        {
            heurValue[0] = -100;
            heurValue[1] = -100;
            heurValue[2] = -100;
        }
        else if(type == EnvironmentType.Colline)
        {
            heurValue[0] = 0;
            heurValue[1] = 2;
            heurValue[2] = 0;
        }
        else if(type == EnvironmentType.Plaine)
        {
            heurValue[0] = 2;
            heurValue[1] = 0;
            heurValue[2] = 1;
        }
        return heurValue;
    }
    public static void WorkerHeuristic(double[][] distanceMatrix, int workerId, ArrayList<TileHeuristic> tileList)
    {
        double distance = 0;
        int newHeur;
        for(TileHeuristic tile : tileList)
        {
            distance = distanceMatrix[(int)workerId][tile.getTileId()];
            if (distance != 0)
                newHeur = tile.getHeuristicValue() - (int)distance*2;
            else
                newHeur =tile.getHeuristicValue() + 50;
            tile.setHeuristicValue(newHeur);
        }

    }
}
