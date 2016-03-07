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

public class ParamParser {

	
	Document document;
	HashMap<String, PrmParameter> params;

	/** CONSTRUCTOR **/
	public ParamParser(String xmlPath) {
		super();

		params = new HashMap<String, PrmParameter>();
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
	public PrmConfig getMetaInstance() {
		PrmConfig prmConfig = readPackageNode(document.getDocumentElement());
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");

		return prmConfig;
	}

	/** READER METHODS **/
	public PrmConfig readPackageNode(Element packageNode) {

		PrmConfig prmConfig= new PrmConfig();
		prmConfig.setLanguageType(packageNode.getTagName());
		
		NodeList nl = packageNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "model") {
				prmConfig.addparametersModel(readModelNode((Element) childNode));
			}
			if (childNode.getNodeName() == "primitive") {
				prmConfig.addparametersPrimitif(readPrimitifNode((Element) childNode));
			}
		}
		return prmConfig;
	}
	
	
	public PrmModel readModelNode(Element entityNode) {
		PrmModel model= new PrmModel();
		
		// recuperation du parents s'il existe
		String nomModel = entityNode.getAttribute("name");
		if (!nomModel.isEmpty() && nomModel != null) {
			model.setName(nomModel);
		}
		String nomPackage = entityNode.getAttribute("package");
		if (!nomPackage.isEmpty() && nomPackage != null) {
			model.setPkg(nomPackage);
		}else{
			model.setPkg(null);
		}
		
		
		return model;
	}
	
	public PrmPrimitif readPrimitifNode(Element entityNode) {
		PrmPrimitif primitif=new PrmPrimitif();
		
		String nomPrimitif = entityNode.getAttribute("name");
		if (!nomPrimitif.isEmpty() && nomPrimitif != null) {
			primitif.setName(nomPrimitif);
		}
		String nomPackage = entityNode.getAttribute("package");
		if (!nomPackage.isEmpty() && nomPackage != null) {
			primitif.setPkg(nomPackage);
		}else{
			primitif.setPkg(null);
		}
		String type = entityNode.getAttribute("type");
		if (!nomPackage.isEmpty() && nomPackage != null) {
			primitif.setType(type);
		}
		
		return primitif;
	}
}
