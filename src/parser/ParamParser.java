package parser;

import java.io.File;
import java.io.IOException;
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

import modelParameter.PrmConfig;
import modelParameter.PrmModel;
import modelParameter.PrmParameter;
import modelParameter.PrmPrimitif;

/**
 * The Class ParamParser.
 */
public class ParamParser {

	/** The document. */
	Document document;

	/** The paramameters mapped by name */
	HashMap<String, PrmParameter> params;

	/**
	 * Parser constructor.
	 *
	 * @param xmlPath
	 *            the xml path
	 */
	public ParamParser(String xmlPath) {
		super();

		params = new HashMap<String, PrmParameter>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();

		document = null;
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(xmlPath));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate an instance of paramconfig, which contains all parameters set in the config file.
	 *
	 * @return the meta instance
	 */
	public PrmConfig getMetaInstance() {
		PrmConfig prmConfig = readPackageNode(document.getDocumentElement());
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");

		return prmConfig;
	}

	/**
	 * Read package node.
	 *
	 * @param packageNode
	 *            the package node
	 * @return the instance of param config
	 */
	public PrmConfig readPackageNode(Element packageNode) {

		PrmConfig prmConfig = new PrmConfig();
		prmConfig.setLanguageType(packageNode.getTagName());

		NodeList nl = packageNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "model") {
				prmConfig
						.addParameter(readModelNode((Element) childNode));
			}
			if (childNode.getNodeName() == "primitive") {
				prmConfig
						.addParameter(readPrimitifNode((Element) childNode));
			}
		}
		return prmConfig;
	}

	/**
	 * Read model node.
	 *
	 * @param entityNode
	 *            the entity node
	 * @return the prm model
	 */
	public PrmModel readModelNode(Element entityNode) {
		PrmModel model = new PrmModel();

		// recuperation du parents s'il existe
		String nomModel = entityNode.getAttribute("name");
		if (!nomModel.isEmpty() && nomModel != null) {
			model.setName(nomModel);
		}
		String nomPackage = entityNode.getAttribute("package");
		if (!nomPackage.isEmpty() && nomPackage != null) {
			model.setPkg(nomPackage);
		} else {
			model.setPkg(null);
		}

		return model;
	}

	/**
	 * Read primitif node.
	 *
	 * @param entityNode
	 *            the entity node
	 * @return the prm primitif
	 */
	public PrmPrimitif readPrimitifNode(Element entityNode) {
		PrmPrimitif primitif = new PrmPrimitif();

		String nomPrimitif = entityNode.getAttribute("name");
		if (!nomPrimitif.isEmpty() && nomPrimitif != null) {
			primitif.setName(nomPrimitif);
		}
		String nomPackage = entityNode.getAttribute("package");
		if (!nomPackage.isEmpty() && nomPackage != null) {
			primitif.setPkg(nomPackage);
		} else {
			primitif.setPkg(null);
		}
		String type = entityNode.getAttribute("type");
		if (!nomPackage.isEmpty() && nomPackage != null) {
			primitif.setType(type);
		}
		String boolPrimitif = entityNode.getAttribute("primitif");
		if (boolPrimitif.equals("true")) {
			primitif.setPrimitif(true);
		} else {
			primitif.setPrimitif(false);
		}

		return primitif;
	}
}
