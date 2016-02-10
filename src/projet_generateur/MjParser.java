package projet_generateur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.logging.*;

public class MjParser {
	Document document;
	HashMap<String, MjType> types;

	/** CONSTRUCTOR **/
	public MjParser(String xmlPath) {
		super();
		
		types = new HashMap<String, MjType>();
		
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

	/** GETTERS SETTERS **/
	public Document getDocument() {
		return document;
	}

	public void setDoc(Document document) {
		this.document = document;
	}

	/** JAVA PRIMITIVES **/
	public static ArrayList<MjType> getJavaPrimitives(){
		// au lieu de creer des entity, faire des reftype avec les primitives
		ArrayList<MjType> primitives = new ArrayList<MjType>();
		primitives.add(new MjReference("String"));
		primitives.add(new MjReference("int"));
		return primitives;
	}
	
	/** EXPORT **/
	public MjPackage getMetaInstance() {
		MjPackage pkg = readPackageNode(document.getDocumentElement());
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");
		
		return pkg;
	}

	/** READER METHODS **/
	public MjPackage readPackageNode(Element packageNode) {
		MjPackage pkg = new MjPackage(packageNode.getAttribute("name"));
		
		// Initialisation des types
		instanciatePrimitivesTypes(getJavaPrimitives());
		instantiateEntityTypes(packageNode.getElementsByTagName("entity"));
		instantiateRefTypes(packageNode.getElementsByTagName("refdef"));
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

	public MjEntity readEntityNode(Element entityNode) {
		MjEntity entity = new MjEntity(entityNode.getAttribute("id"));

		NodeList nl = entityNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeName() == "attribute") {
				MjAttribute attr = readAttributeNode((Element) childNode);
				if (attr != null)
					entity.addAttribute(readAttributeNode((Element) childNode));
			}
		}
		return entity;
	}

	public MjAttribute readAttributeNode(Element attributeNode){
		MjAttribute attribute = new MjAttribute(attributeNode.getAttribute("name"));
		
        String typeName = attributeNode.getAttribute("type-id");
        
        if(typeName == null)
        	return null;
		attribute.setType(this.types.get(typeName));
        return attribute;
	}
	
	
	/** TYPES INSTANTIATION **/
	private void instanciatePrimitivesTypes(ArrayList<MjType> javaPrimitives) {
		for(MjType primitivType : javaPrimitives){
			types.put(primitivType.getId(), primitivType);
		}
	}
	
	public void instantiateEntityTypes(NodeList entityNodes){
		for(int i=0; i<entityNodes.getLength(); i++){
			Element listNode = (Element)entityNodes.item(i);
			MjReference entityRef = new MjReference(listNode.getAttribute("id"));
			this.types.put(entityRef.getId(), entityRef);
		}
	}
	
	public void instantiateRefTypes(NodeList typeNodes){
		for(int i=0; i<typeNodes.getLength(); i++){
			// regarder ici si c est deja present dans les primitives 
			Element refNode = (Element)typeNodes.item(i);
			MjReference ref = new MjReference(refNode.getAttribute("id"));
			this.types.put(ref.getId(), ref);
		}
	}
	
	public void instantiateListTypes(NodeList listNodes){
		for(int i=0; i<listNodes.getLength(); i++){
			Element listNode = (Element)listNodes.item(i);
			MjType type = this.types.get(listNode.getAttribute("type-list"));
			MjList list = new MjList(listNode.getAttribute("id"), type);
			this.types.put(list.getId(), list);
		}
	}
	

	

	

	
}
