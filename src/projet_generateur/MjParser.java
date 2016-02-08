package projet_generateur;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class MjParser {
	Document document;
	MjPackage primitifPackage;

	/** CONSTRUCTOR **/
	public MjParser(String xmlPath) {
		super();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

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
	public MjPackage getMetaInstance(){
		MjPackage pkg = readPackageNode(document.getDocumentElement());
		System.out.println("File read!");
		return pkg;
	}
	
	
	/** VISIT METHODS **/
	public MjPackage readPackageNode(Element packageNode){
		MjPackage pkg = new MjPackage(packageNode.getAttribute("name"));

        NodeList nl = packageNode.getChildNodes();
        for(int i=0;i<nl.getLength();i++) {
        	Node childNode = nl.item(i);
        	if(childNode.getNodeName() == "entity"){
        		pkg.addEntity(readEntityNode((Element)childNode));
        	}
        }
        return pkg;
	}
	
	public MjEntity readEntityNode(Element entityNode){
		MjEntity entity = new MjEntity(entityNode.getAttribute("name"));

        NodeList nl = entityNode.getChildNodes();
        for(int i=0;i<nl.getLength();i++) {
        	Node childNode = nl.item(i);
        	if(childNode.getNodeName() == "attribute"){
        		MjAttribute attr = readAttributeNode((Element)childNode);
        		if(attr != null)
        			entity.addAttribute(readAttributeNode((Element)childNode));
        	}
        }
        return entity;
	}
	
	public MjAttribute readAttributeNode(Element attributeNode){
		MjAttribute attribute = new MjAttribute(attributeNode.getAttribute("name"));
		
        String typeName = attributeNode.getAttribute("type");
        
        if(typeName == null)
        	return null;
        
        attribute.setType(new MjType(typeName));
        
        return attribute;
	}
	
}
