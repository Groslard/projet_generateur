package projet_generateur;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MjParser {
	Document document;
	MjPackage primitifPackage;
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

		primitifPackage = MjPackage.getJavaPrimitives();
	}

	/** GETTERS SETTERS **/
	public Document getDocument() {
		return document;
	}

	public void setDoc(Document document) {
		this.document = document;
	}

	/** EXPORT **/
	public MjPackage getMetaInstance() {
		MjPackage pkg = readPackageNode(document.getDocumentElement());
		System.out.println("File read!");
		return pkg;
	}

	/** VISIT METHODS **/
	public MjPackage readPackageNode(Element packageNode) {
		MjPackage pkg = new MjPackage(packageNode.getAttribute("name"));
		
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
		MjEntity entity = new MjEntity(entityNode.getAttribute("name"));

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
		
        String typeName = attributeNode.getAttribute("type");
        
        if(typeName == null)
        	return null;
		attribute.setType(this.types.get(attributeNode.getAttribute("type-id")));
        return attribute;
	}
	
	public void instantiateRefTypes(NodeList refTypes){
		for(int i=0; i<refTypes.getLength(); i++){
			Element refNode = (Element)refTypes.item(i);
			MjReference ref = new MjReference(refNode.getAttribute("type"));
			this.types.put(refNode.getAttribute("id"), ref);
		}
	}
	
	public void instantiateListTypes(NodeList listTypes){
		for(int i=0; i<listTypes.getLength(); i++){
			Element listNode = (Element)listTypes.item(i);
			MjType type = this.types.get(listNode.getAttribute("type-list-id"));
			MjList list = new MjList(type);
			this.types.put(listNode.getAttribute("id"), list);
		}
	}
	
//	public MjType readTypeNode(Element attributeNode){
//		String min = attributeNode.getAttribute("min");
//    	String max = attributeNode.getAttribute("max");
//    	
//    	MjAttribute attribute = new MjAttribute(attributeNode.getAttribute("name"));
//		// tester si c est un noeud type ou list
////    	attribute .setType(new MjList(attributeNode.getAttribute("list-type"), 
////    			(min.isEmpty())?0:Integer.parseInt(min), 
////    			(max.isEmpty())?0:Integer.parseInt(max)));
//        
//        return attribute;
//	}
}
