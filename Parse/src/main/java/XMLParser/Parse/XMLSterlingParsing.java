package XMLParser.Parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLSterlingParsing {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		File fXmlFile = new File("./resources/SterlingServices.xml");
		if (fXmlFile.exists()) {
			Document doc = dbBuilder.parse(fXmlFile);
			Element docEle = doc.getDocumentElement();

			// Print root element of the document
			System.out.println("Root element of the document: " + docEle.getNodeName());

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Server");

			// Print total student elements in document
			System.out.println("Total Servers: " + nList.getLength());

			System.out.println("=====================");

			String attribute_01 = "Id";
			String attribute_02 = "Name";
			String attribute_03 = "Type";
			String attribute_04 = "Status";

			if (nList != null && nList.getLength() > 0)
				for (int i = 0; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					Element eElement = (Element) nNode;
					System.out.println("TAG:" + nNode.getNodeName());
					System.out.println("ID:" + eElement.getAttribute(attribute_01));
					System.out.println("NAME:" + eElement.getAttribute(attribute_02));
					System.out.println("STATUS:" + eElement.getAttribute(attribute_03));
					System.out.println("TYPE:" + eElement.getAttribute(attribute_04));

					HashMap<String, List<String>> hmap = new HashMap<String, List<String>>();

					String key = eElement.getAttribute(attribute_02);

					List<String> value = new ArrayList<String>();

					value.add(eElement.getAttribute(attribute_01));
					value.add(eElement.getAttribute(attribute_03));
					value.add(eElement.getAttribute(attribute_04));

					hmap.put(key, value);

					for (String Key : hmap.keySet()) {
						List<String> atributes = hmap.get(Key);
						System.out.println("Key :" + Key);
						System.out.println("Value : " + atributes);

						System.out.println("=====================");
					}
				}
		}
	}
}
