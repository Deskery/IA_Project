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
            Location location;
            EnvironmentType environmentType;
            EnvironmentCharacteristics environmentCharacteristics;
            Resource resource;
            boolean amenagement;

            NodeList casesList = document.getElementsByTagName("Tile");
            for (int i = 0; i < casesList.getLength(); i++) {
                org.w3c.dom.Node node = casesList.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    location = getCaseLocationFromElement(element);
                    environmentType = getETFromElement(element);
                    environmentCharacteristics = getECFromElement(element);
                    resource = getResourceFromElement(element);
                    amenagement = hasAnAmenagement(element);

                    map.addCase(location, environmentType, environmentCharacteristics, resource, amenagement);
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | NumberFormatException e) {
            e.printStackTrace();
        }

        return map;
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

    private static Location getCaseLocationFromElement(Element element) {
        float locationX = Float.valueOf(element.getElementsByTagName("LocationX").item(0).getTextContent().trim());
        float locationY = Float.valueOf(element.getElementsByTagName("LocationY").item(0).getTextContent().trim());
        return new Location(locationX, locationY);
    }

    public static ArrayList<Player> generatePlayers(String playerPath) {
        return new ArrayList<Player>();
    }

    public static ArrayList<WorkerAgent> generateWorker(int idPlayer, String workerPath) {
        return new ArrayList<WorkerAgent>();
    }
}
