package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.logging.*;

public class MiniSpecParser {
	Document document;
	HashMap<String, MsEntity> entities;

	/** CONSTRUCTOR **/
	public MiniSpecParser(String xmlPath) {
		super();

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		entities = new HashMap<String, MsEntity>();


		document = null;
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(xmlPath));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	/** GETTERS SETTERS **/
	public Document getDocument() {
		return document;
	}

	public void setDoc(Document document) {
		this.document = document;
	}

	/** EXPORT **/
	public MsModel getMetaInstance() {
		MsModel mdl = readPackageNode(document.getDocumentElement());
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");

		return mdl;
	}

	/** READER METHODS **/
	public MsModel readPackageNode(Element packageNode) {

		MsModel mdl = new MsModel(packageNode.getAttribute("name"));

		// Initialisation des types
		instantiateEntityTypes(packageNode.getElementsByTagName("entity"));
		instantiateListTypes(packageNode.getElementsByTagName("listdef"));

		NodeList nl = packageNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "entity") {
				mdl.addEntity(readEntityNode((Element) childNode));
			}
		}
		return mdl;
	}

	public MsEntity readEntityNode(Element entityNode) {
		MsEntity entity = entities.get(entityNode.getAttribute("id"));
		
		// recuperation du parents s'il existe
		String nomParent = entityNode.getAttribute("parent");
		if (!nomParent.isEmpty() && nomParent != null) {
			entity.setParent(types.get(nomParent));
		}

		NodeList nl = entityNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "attribute") {
				MsAttribute attr = readAttributeNode((Element) childNode);
				if (attr != null)
					entity.addAttribute(attr);
			}
		}
		return entity;
	}

	public MsAttribute readAttributeNode(Element attributeNode) {
		MsAttribute attribute = new MsAttribute(attributeNode.getAttribute("name"));

		String typeName = attributeNode.getAttribute("type-id");

		if (typeName == null)
			return null;
		attribute.setType(this.types.get(typeName));
		return attribute;
	}

	
	public void instantiateEntityTypes(NodeList entityNodes){
		for(int i=0; i<entityNodes.getLength(); i++){
			Element entityNode = (Element)entityNodes.item(i);
			MsEntity entity = new MsEntity(entityNode.getAttribute("id"));
			entities.put(entity.getName(), entity);
		}
	}
	
	public void instantiateListTypes(NodeList listNodes){
		for(int i=0; i<listNodes.getLength(); i++){
			Element listNode = (Element)listNodes.item(i);
			MsType type = this.types.get(listNode.getAttribute("type-list"));
			MsList list = new MsList(listNode.getAttribute("id"), type);
			String min = listNode.getAttribute("min");
			String max = listNode.getAttribute("max");
			list.setMin(min==""?0:Integer.parseInt(min));
			list.setMax(min==""?0:Integer.parseInt(max));
			
			this.types.put(list.getId(), list);
		}
	}

}
