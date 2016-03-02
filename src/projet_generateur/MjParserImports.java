package projet_generateur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MjParserImports {

	
	Document document;
	HashMap<String, MjParserImports> primitives;

	/** CONSTRUCTOR **/
	public MjParserImports(String xmlPath) {
		super();

		primitives = new HashMap<String, MjParserImports>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();



		document = null;
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(xmlPath));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/** EXPORT **/
	public ArrayList<MjParserImports> getMetaInstance() {
		ArrayList<MjParserImports> pkg = readPackageNode(document.getDocumentElement());
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");

		return pkg;
	}

	/** READER METHODS **/
	public ArrayList<MjParserImports> readPackageNode(Element packageNode) {

		ArrayList<MjParserImports> pkg = ;

		// Initialisation des types
		instanciatePrimitivesTypes(getJavaPrimitives());
		instantiateRefTypes(packageNode.getElementsByTagName("refdef"));
		instantiateEntityTypes(packageNode.getElementsByTagName("entity"));
		instantiateListTypes(packageNode.getElementsByTagName("listdef"));

		NodeList nl = packageNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "entity") {
				pkg.addEntity(readEntityNode((Element) childNode));
			}
		}
		return pkg;
	}
	
	
}
