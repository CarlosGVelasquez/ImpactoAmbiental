package aplicacion.lectorXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LectorFExml {

    public HashMap<String, String> fEMovil = new HashMap<String, String>();
    public HashMap<String, String> fEFija = new HashMap<String, String>();
    public HashMap<String, String> fEElectricidad = new HashMap<String, String>();

    private static String configPath = "src/main/resources/FEconfig.xml";
    private String nombreItem = "";

    public void LectorFExml() throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(configPath));

        document.getDocumentElement().normalize();

        try {
            NodeList nListCF = document.getElementsByTagName("CombustionFija");
            Node nodeCF = nListCF.item(0);
            guardoHijos(nodeCF, fEFija);

            NodeList nListCM = document.getElementsByTagName("CombustionMovil");
            Node nodeCM = nListCM.item(0);
            guardoHijos(nodeCM, fEMovil);

            NodeList nListElec = document.getElementsByTagName("ElectricidadConsumida");
            Node nodeElec = nListElec.item(0);
            guardoHijos(nodeElec, fEElectricidad);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void modificarValorFE(HashMap<String, String> FEModif, String factorEmision, String valor ) {
        FEModif.put(factorEmision, valor);
    }


    private void guardoHijos(Node nodeCM, HashMap<String, String> fEPadre){
        if (nodeCM.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nodeCM;
            for (Integer itr = 1;  itr < nodeCM.getChildNodes().getLength(); itr = itr+2){
                nombreItem = nodeCM.getChildNodes().item(itr).getNodeName();
                fEPadre.put(nombreItem, eElement.getElementsByTagName(nombreItem).item(0).getTextContent());
            }
        }
    }
}
