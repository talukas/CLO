package XMLParser.Parse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hamcrest.Matcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParserTest extends ParseXML {

	NodeList nList;
	Node nNode;
	Element eElement;
	String csvFile = "./resources/ServersList.csv";
	Map<String, String> hmap;
	String key;
	String value;

	@BeforeTest
	public void beforeClass() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		File fXmlFile = new File("./resources/SterlingServices.xml");
		if (fXmlFile.exists()) {
			Document doc = dbBuilder.parse(fXmlFile);
			Element docEle = doc.getDocumentElement();

			// Print root element of the document
			System.out.println("Root element of the document: " + docEle.getNodeName());

			doc.getDocumentElement().normalize();

			nList = doc.getElementsByTagName("Server");

			// Print total elements in document
			System.out.println("Total Servers: " + nList.getLength());

			System.out.println("=====================");

			// String attribute_01 = "Id";
			String attribute_02 = "Name";
			// String attribute_03 = "Type";
			String attribute_04 = "Status";

			if (nList != null && nList.getLength() > 0)
				for (int i = 0; i < nList.getLength(); i++) {
					nNode = nList.item(i);
					eElement = (Element) nNode;

					hmap = new HashMap<String, String>();

					key = eElement.getAttribute(attribute_02);
					value = eElement.getAttribute(attribute_04);

					// List<String> value = new ArrayList<String>();

					// value.add(eElement.getAttribute(attribute_01));
					// value.add(eElement.getAttribute(attribute_03));
					// value.add(eElement.getAttribute(attribute_04));

					hmap.put(key, value);
					System.out.println("Actual:" + " " + key + " " + value);

					//for (String Key : hmap.keySet()) {
						// List<String> atributes = hmap.get(Key);
						// System.out.println("Key :" + Key);
						// System.out.println("Value : " + atributes);

						// System.out.println("=====================");
					}

				}
		}
	

	@Test(dataProvider = "dp")
	public void convert(String Name, String Status) {

		Map<String, String> map = new HashMap<String, String>();
		String key2 = "DesignStudioDCCancel" ;
		String value2 = "Active";
		
		map.put(key2 , value2);
		//System.out.println("Expected:" + " " + key + " " + value);

		//for (Entry Key : map.keySet()) {
		// String atributes = map.get(Key);
		// System.out.println("Key :" + Key);
		// System.out.println("Value : " + atributes);
		
		map.get(hmap);
		assertEquals(map, hmap);
		
		//System.out.println(hmap.equals(key2));
		//System.out.println(one);
		
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
