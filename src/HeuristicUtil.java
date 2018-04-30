import java.util.ArrayList;

/*
Classe statique dédiée au calcul des heuristiques pour les tuiles de jeu.
Elles sont utilisés principalement dans le superviseur
La banque de ressources du joueur n'est pas pris en compte et la valeur des ressources n'a pas d'explication autre que
pour montrer l'efficacité de l'algorithme
On considère que l'aménagemnt créer est l'optimal de la case et qu'il ne détruit rien
 */

public class HeuristicUtil {

    public static final int foodFactor = 5;
    public static final int prodFactor = 4;
    public static final int goldFactor = 3;

    // Donne une valeur d'heuristique pour une case donnée
    public static int CheckTileHeuristic(Tile tile)
    {
        int heurValue;

        heurValue = addVars(testCharacteristics(tile.getEnvironmentCharacteristics()),testType(tile.getEnvironmentType()),testRessources(tile.getResource()));
        if(tile.isAmenagement())
            heurValue = -1000;

        return heurValue;
    }

    // permets d'additioner les valeurs de tableaux d'entier pour avoir une valeur d'heuristique sur un entier
    private static int addVars(int[] t1,int [] t2, int[] t3) // on aprt du principe que cette fonction n'est utilisée que dans ce contexte
    {
        int[] heurValue = new int[3]; // nourriture prod or possibilit de rajouté un 4 ème pouur les ressources annexe
        int finalValue = 0;
        for(int i=0;i<3;i++) {
            heurValue[i] = t1[i] + t2[i] + t3[i];
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

    //Renvoie un tableau de 3 entiers correspondant a la plus value d'exploitation de la ressources
    private static int[] testRessources(Resource r)
    {
        int[] heurValue = new int[3]; // nourriture, production, or, et la  possibilité de rajouter un 4 ème pour les ressources annexe (science)
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
        else if(r instanceof  SpecialResource)
        {
            heurValue[0] = 0;
            heurValue[1] = 3;
            heurValue[2] = 1;
        }
        else
        {
            heurValue[0] = 2;
            heurValue[1] = 2;
            heurValue[2] = 10;
        }
        return heurValue;
    }


    //Renvoie un tableau de 3 entiers correspondant a la plus value d'amenagement de la case , les cas particuliers ont été ignorés ( ex : possibilité de la presence de riviere sur toutes les cases)
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

    //Renvoie un tableau de 3 entiers correspondant a la plus value d'amengament de la case en fonction du type
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

    //Rajoute a la valeur heuristique des tuiles la prise en compte de la distance de l'ouvrier
    public static void WorkerHeuristic(double[][] distanceMatrix, int workerId, ArrayList<TileHeuristic> tileList)
    {
        double distance;
        int newHeur;
        for(TileHeuristic tile : tileList)
        {
            distance = distanceMatrix[workerId][tile.getTileId()];
            if (distance != 0)
                newHeur = tile.getHeuristicValue() - (int)distance*2;
            else
                newHeur =tile.getHeuristicValue() + 200;
            tile.setHeuristicValue(newHeur);
        }

    }
}
