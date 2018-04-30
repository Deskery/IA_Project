import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader {
    public static Map generateMap(String filePath) {
        Map map = new Map();

        try {

            File xmlFileData = new File(filePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFileData);

            // Load the list of Cases
            int id;
            EnvironmentType environmentType;
            EnvironmentCharacteristics environmentCharacteristics;
            Resource resource;
            boolean amenagement;
            Location location;
            NodeList neighbors;

            NodeList casesList = document.getElementsByTagName("Tile");
            for (int i = 0; i < casesList.getLength(); i++) {
                org.w3c.dom.Node node = casesList.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    id = getIDFromElement(element);
                    environmentType = getETFromElement(element);
                    environmentCharacteristics = getECFromElement(element);
                    resource = getResourceFromElement(element);
                    amenagement = hasAnAmenagement(element);

                    Tile tile = new Tile(id, environmentType, environmentCharacteristics, resource, amenagement);

                    map.addCase(id, environmentType, environmentCharacteristics, resource, amenagement);
                }
            }

            // Pour toutes les tuiles
            for (int i = 0; i < casesList.getLength(); i++) {
                org.w3c.dom.Node node = casesList.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int idTuile = getIDFromElement(element);

                    neighbors = ((Element) node).getElementsByTagName("Neighbor");

                    // On ajoute tous les voisins d'une tuile
                    for(int j = 0; j < neighbors.getLength() ; j++) {
                        org.w3c.dom.Node neighbor = neighbors.item(j);
                        int idN;
                        idN = Integer.valueOf(neighbors.item(j).getTextContent().trim());
                        map.getTileById(idTuile).addNeighbour(map.getTileById(idN));
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | NumberFormatException e) {
            e.printStackTrace();
        }

        return map;
    }

    private static Location getLocationFromElement(Element element) {
        int x = Integer.valueOf(element.getElementsByTagName("LocationX").item(0).getTextContent().trim());
        int y = Integer.valueOf(element.getElementsByTagName("LocationY").item(0).getTextContent().trim());
        return new Location(x, y);
    }

    private static int getIDFromElement(Element element) {
        return Integer.valueOf(element.getElementsByTagName("Id").item(0).getTextContent().trim());
    }

    private static boolean hasAnAmenagement(Element element) {
        String amenagement = element.getElementsByTagName("Amenagement").item(0).getTextContent();
        return amenagement.equals("True");
    }

    private static Resource getResourceFromElement(Element element) {
        Resource resource;

        String resourceType = element.getElementsByTagName("ResourceType").item(0).getTextContent();
        float resourceQuantity = Float.valueOf(element.getElementsByTagName("ResourceQuantity").item(0).getTextContent().trim());

        switch (resourceType) {
            case "Luxury":
                resource = new LuxuryResource(resourceQuantity);
                break;
            case "Special":
                resource = new SpecialResource(resourceQuantity);
                break;
            case "Usefull":
                resource = new UsefullResource(resourceQuantity);
                break;
            default:
                throw new IllegalArgumentException("Invalid resource type: " + resourceType);
        }

        return resource;
    }

    private static EnvironmentCharacteristics getECFromElement(Element element) {
        String ec = element.getElementsByTagName("EnvironmentCharacteristic").item(0).getTextContent();
        EnvironmentCharacteristics environmentCharacteristics;

        switch (ec) {
            case "Desert":
                environmentCharacteristics = EnvironmentCharacteristics.Desert;
                break;
            case "Foret":
                environmentCharacteristics = EnvironmentCharacteristics.Foret;
                break;
            case "Jungle":
                environmentCharacteristics = EnvironmentCharacteristics.Jungle;
                break;
            case "Marais":
                environmentCharacteristics = EnvironmentCharacteristics.Marais;
                break;
            case "Prairie":
                environmentCharacteristics = EnvironmentCharacteristics.Prairie;
                break;
            case "Riviere":
                environmentCharacteristics = EnvironmentCharacteristics.Riviere;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + ec);
        }

        return environmentCharacteristics;
    }

    private static EnvironmentType getETFromElement(Element element) {
        String et = element.getElementsByTagName("EnvironmentType").item(0).getTextContent();
        EnvironmentType environmentType;

        switch (et) {
            case "Colline":
                environmentType = EnvironmentType.Colline;
                break;
            case "Cote":
                environmentType = EnvironmentType.Cote;
                break;
            case "Glace":
                environmentType = EnvironmentType.Glace;
                break;
            case "Lac":
                environmentType = EnvironmentType.Lac;
                break;
            case "Plaine":
                environmentType = EnvironmentType.Plaine;
                break;
            case "Montagne":
                environmentType = EnvironmentType.Montagne;
                break;
            case "Ocean":
                environmentType = EnvironmentType.Ocean;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + et);
        }

        return environmentType;
    }

    public static ArrayList<Player> generatePlayers(String filePath) {
        ArrayList<Player> players = new ArrayList<>();

        try {

            File xmlFileData = new File(filePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFileData);

            // Load the list of Cases
            int id;

            NodeList playerList = document.getElementsByTagName("Player");
            for (int i = 0; i < playerList.getLength(); i++) {
                org.w3c.dom.Node node = playerList.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    id = Integer.valueOf(element.getElementsByTagName("Id").item(0).getTextContent().trim());
                    players.add(new Player(id));
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | NumberFormatException e) {
            e.printStackTrace();
        }

        return players;
    }

    public static ArrayList<WorkerAgent> generateWorker(int idPlayer, String workerPath, Map map) {
        ArrayList<WorkerAgent> workerAgents = new ArrayList<>();
        try {

            File xmlFileData = new File(workerPath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFileData);

            // Load the list of Agents
            int idP, idWorker, idTile;

            NodeList workerList = document.getElementsByTagName("Worker");
            for (int i = 0; i < workerList.getLength(); i++) {
                org.w3c.dom.Node node = workerList.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    idP = Integer.valueOf(element.getElementsByTagName("IdPlayer").item(0).getTextContent().trim());

                    if(idP == idPlayer){
                        idWorker = Integer.valueOf(element.getElementsByTagName("Id").item(0).getTextContent().trim());
                        idTile = Integer.valueOf(element.getElementsByTagName("IdTile").item(0).getTextContent().trim());
                        workerAgents.add(new WorkerAgent(idWorker, map.getTileById(idTile)));
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | NumberFormatException e) {
            e.printStackTrace();
        }

        return workerAgents;
    }
}
