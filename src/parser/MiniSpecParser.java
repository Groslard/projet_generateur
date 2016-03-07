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
import modelMiniSpec.MsType;
import modelMiniSpec.MsUnresolvedType;
import modelMiniSpec.UnresolveObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.logging.*;

public class MiniSpecParser {
	Document document;
	HashMap<String, MsEntity> entities;
	HashMap<String, MsType> typesDef;
	ArrayList<UnresolveObject> unresolvedObjects;

	/** CONSTRUCTOR **/
	public MiniSpecParser(String xmlPath) {
		super();

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		entities = new HashMap<String, MsEntity>();
		
		typesDef = new HashMap<String, MsType>();
		unresolvedObjects = new ArrayList<UnresolveObject>();

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
		MsModel mdl = readModelsNode(document.getDocumentElement()).get(0);
		this.resolveTypes();
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");
		return mdl;
	}

	
	/** READER METHODS **/
	private ArrayList<MsModel> readModelsNode(Element modelsNode) {
		ArrayList<MsModel> res = new ArrayList<MsModel>();
		NodeList nl = modelsNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "model") {
				res.add(readModelNode((Element) childNode));
			}
		}
		return res;
	}
	
	public MsModel readModelNode(Element modelNode) {

		MsModel mdl = new MsModel(modelNode.getAttribute("name"));

		NodeList nl = modelNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if(childNode.getNodeType() != childNode.ELEMENT_NODE)
				continue;
			if (childNode.getNodeName() == "entity") {
				mdl.addEntity(readEntityNode((Element) childNode));
			}else{
				instantiateType((Element) childNode);
			}
		}
		return mdl;
	}

	public MsEntity readEntityNode(Element entityNode) {
		MsEntity entity = new MsEntity(entityNode.getAttribute("id"));
		entities.put(entity.getName(), entity);
		
		// recuperation du parents s'il existe
		String nomParent = entityNode.getAttribute("parent");
		if (!nomParent.isEmpty() && nomParent != null) {
			entity.setParent(new MsUnresolvedType(nomParent));
			unresolvedObjects.add(entity);
		}

		NodeList nl = entityNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if(childNode.getNodeType() != childNode.ELEMENT_NODE)
				continue;
			if (childNode.getNodeName() == "attribute") {
				MsAttribute attr = readAttributeNode((Element) childNode);
				if (attr != null)
					entity.addAttribute(attr);
			}else{
				instantiateType((Element) childNode);
			}
		}
		return entity;
	}

	public MsAttribute readAttributeNode(Element attributeNode) {
		MsAttribute attribute = new MsAttribute(attributeNode.getAttribute("name"));
		String typeName = attributeNode.getAttribute("type");
		attribute.setType(new MsUnresolvedType(typeName));
		this.unresolvedObjects.add(attribute);
		return attribute;
	}

	
	/** GESTION DES TYPES **/
	public void setPrimitives(ArrayList<String> primitivesNames){
		for(String primName:primitivesNames){
			MsEntity primEntity = new MsEntity(primName);
			this.entities.put(primName, primEntity);
		}
	}
	
	private void instantiateType(Element typeDefNode) {
		MsType type = new MsUnresolvedType(typeDefNode.getAttribute("type"));
		MsList list = new MsList(typeDefNode.getAttribute("id"), type);
		String min = typeDefNode.getAttribute("min");
		String max = typeDefNode.getAttribute("max");
		list.setMin(min==""?0:Integer.parseInt(min));
		list.setMax(min==""?0:Integer.parseInt(max));
		
		this.typesDef.put(list.getId(), list);
		this.unresolvedObjects.add(list);
	}

	private void resolveTypes() {
		for(UnresolveObject object:this.unresolvedObjects)
			object.resolve(typesDef, entities);
	}
}
