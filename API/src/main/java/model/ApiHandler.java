package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ApiHandler {

	public static String URL = "https://v2.jokeapi.dev/joke/Any?format=xml&type=single";

	public static String getApi() throws IOException {

		// print and test in a browser
		//System.out.println(URL);

		// Set the URL that will be sent
		URL api_url = new URL(URL);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");
		

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {

			//System.out.println(inputLine);
			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close();

		// print the response (The response is in String format)
		//System.out.println(ApiResponse);

		// Call a method to make a XMLdoc out of the full String response
		Document doc = convertStringToXMLDocument(ApiResponse);
		System.out.print(doc);
		
		// check that the XML response is OK by getting the Root element
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// Create a Node list that gets everything in and under the "joke" tag
		NodeList nList = doc.getElementsByTagName("joke"); // This returns a list but we know in this example that there is only one Joke element. Otherwise we should do a loop
		Element jokeElement = (Element) nList.item(0); //Since there is only one Joke element, it should be at 0 index
		String joke = jokeElement.getTextContent(); // We get the content the element has

		return joke;
	}

	// Method the makes a XML doc out of a string, if it is in a XML format.
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
