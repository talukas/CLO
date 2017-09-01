package XMLParser.Parse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParserTest {

	NodeList nList;
	Node nNode;
	Element eElement;
	String csvFile = "./resources/ServersList.csv";
	Map<String, String> hmap = new HashMap<>();
	String attribute_02 = "Name";
	String attribute_04 = "Status";
	String actualName;
	String expected;
	String actual;
	String actualStatus;
	String expectedStatus;
	String Name;
	String Status;

	@BeforeTest
	public void beforeMethod() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		File fXmlFile = new File("./resources/SterlingServices.xml");
		if (fXmlFile.exists()) {
			Document doc = dbBuilder.parse(fXmlFile);
			// Element docEle = doc.getDocumentElement();

			// Print root element of the document
			// System.out.println("Root element of the document: " + docEle.getNodeName());

			doc.getDocumentElement().normalize();

			nList = doc.getElementsByTagName("Server");

			String attribute_02 = "Name";
			String attribute_04 = "Status";

			if (nList != null && nList.getLength() > 0)
				for (int i = 0; i < nList.getLength(); i++) {
					nNode = nList.item(i);
					eElement = (Element) nNode;

					Name = eElement.getAttribute(attribute_02);
					Status = eElement.getAttribute(attribute_04);
					hmap.put(Name, Status);

					hmap.get(Name);

				}
		}
	}

	@Test(dataProvider = "dp")
	public void convert(String ServiceName, String ServiceStatusExpect) {

		String ServiseStatusActual = hmap.get(ServiceName);

		assertThat(ServiseStatusActual, is("Active"));

	}

	@DataProvider(name = "dp")

	public Iterator<String[]> american() throws InterruptedException, IOException {

		String csvLine = "";

		String[] a = null;

		ArrayList<String[]> al = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(csvFile));

		while ((csvLine = br.readLine()) != null) {

			a = csvLine.split(",");

			al.add(a);

		}

		br.close();
		return al.iterator();
	}

}
