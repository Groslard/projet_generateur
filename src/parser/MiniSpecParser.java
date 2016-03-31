package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsCollection;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsSet;
import modelMiniSpec.MsType;
import modelMiniSpec.MsUnresolvedType;
import modelMiniSpec.UnresolveObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.logging.*;

/**
 * The Class MiniSpecParser.
 */
public class MiniSpecParser {

	/** The document. */
	Document document;

	/** The entities. */
	HashMap<String, MsEntity> entities;

	/** List of minispec types mapped by their name */
	HashMap<String, MsType> typesDef;

	/** The unresolved objects. */
	ArrayList<UnresolveObject> unresolvedObjects;

	/** The current parsing entity. */
	MsEntity currentEntity;

	/** The current parsing model. */
	MsModel currentModel;

	/**
	 * Parser constructor.
	 * 
	 * @param xmlPath
	 *            the xml path
	 */
	public MiniSpecParser(String xmlPath) {
		super();

		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();

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

	/**
	 * Document getter.
	 *
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Sets the doc.
	 *
	 * @param document
	 *            the new doc
	 */
	public void setDoc(Document document) {
		this.document = document;
	}

	/**
	 * Generate the minispec model.
	 *
	 * @return The minispec model corresponding to the file
	 * @throws Exception
	 *             the exception
	 */
	public MsModel getMetaInstance() throws Exception {
		MsModel mdl = readModelsNode(document.getDocumentElement()).get(0);
		this.resolveTypes();
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "XML Read");
		Set cles = this.entities.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()) {
			Object cle = it.next();

			MsEntity valeur = this.entities.get(cle);
			verifHeritage(valeur);
		}
		return mdl;
	}

	/**
	 * Read all models nodes.
	 *
	 * @param modelsNode
	 *            the models node
	 * @return the list of minispec models
	 * @throws Exception
	 *             the exception
	 */
	private ArrayList<MsModel> readModelsNode(Element modelsNode)
			throws Exception {
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

	/**
	 * Read single model node.
	 *
	 * @param modelNode
	 *            the model node
	 * @return the minispec model
	 * @throws Exception
	 *             the exception
	 */
	public MsModel readModelNode(Element modelNode) throws Exception {

		MsModel mdl = new MsModel(modelNode.getAttribute("name"));
		currentModel = mdl;
		NodeList nl = modelNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (childNode.getNodeName() == "entity") {
				mdl.addEntity(readEntityNode((Element) childNode));
			} else {
				instantiateType((Element) childNode);
			}
		}
		return mdl;
	}

	/**
	 * Read entity node.
	 *
	 * @param entityNode
	 *            the entity node
	 * @return the minispec entity
	 * @throws Exception
	 *             the exception
	 */
	public MsEntity readEntityNode(Element entityNode) throws Exception {
		MsEntity entity = new MsEntity(entityNode.getAttribute("id"),
				currentModel);
		entities.put(entity.getName(), entity);

		// recuperation du parents s'il existe
		String nomParent = entityNode.getAttribute("parent");
		if (!nomParent.isEmpty() && nomParent != null) {
			entity.setParent(new MsUnresolvedType(nomParent));
			unresolvedObjects.add(entity);
		}
		currentEntity = entity;
		NodeList nl = entityNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (childNode.getNodeName() == "attribute") {
				MsAttribute attr = readAttributeNode((Element) childNode);
				if (attr != null)
					entity.addAttribute(attr);
			} else {
				instantiateType((Element) childNode);
			}
		}
		return entity;
	}

	/**
	 * Read attribute node.
	 *
	 * @param attributeNode
	 *            the attribute node
	 * @return the minispec attribute
	 */
	public MsAttribute readAttributeNode(Element attributeNode) {
		MsAttribute attribute = new MsAttribute(
				attributeNode.getAttribute("name"), currentEntity);

		// gestion constructor method
		if (attributeNode.getAttribute("constructor") != null) {
			String val = attributeNode.getAttribute("constructor");
			if (val.equals("true")) {
				attribute.setConstructor(true);
			} else {
				attribute.setConstructor(false);
			}

		} else {
			attribute.setConstructor(false);
		}
		if (attributeNode.getAttribute("method") != null) {
			String val = attributeNode.getAttribute("method");
			attribute.setMethod(val);
		} else {
			attribute.setMethod(null);
		}

		String typeName = attributeNode.getAttribute("type");
		attribute.setType(new MsUnresolvedType(typeName));
		this.unresolvedObjects.add(attribute);

		NodeList nl = attributeNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node childNode = nl.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (childNode.getNodeName() == "value") {
				String val = childNode.getTextContent();
				attribute.setInitialValue(val);
			}

		}

		return attribute;
	}

	/**
	 * primitives initialisation.
	 *
	 * @param primitivesNames
	 *            names of primitives defines by the user.
	 */
	public void setPrimitives(ArrayList<String> primitivesNames) {
		for (String primName : primitivesNames) {
			MsEntity primEntity = new MsEntity(primName, currentModel);
			this.entities.put(primName, primEntity);
		}
	}

	/**
	 * Instantiate type with typedef node.
	 *
	 * @param typeDefNode
	 *            the type def node
	 * @throws Exception
	 *             the exception
	 */
	private void instantiateType(Element typeDefNode) throws Exception {
		MsCollection collection = null;
		String _min = typeDefNode.getAttribute("min");
		String _max = typeDefNode.getAttribute("max");
		int min = _min == "" ? 0 : Integer.parseInt(_min);
		int max = _max == "" ? 0 : Integer.parseInt(_max);
		MsType type = new MsUnresolvedType(typeDefNode.getAttribute("type"));

		if (typeDefNode.getNodeName() == "listdef")
			collection = new MsList(type, min, max);
		else if (typeDefNode.getNodeName() == "setdef")
			collection = new MsSet(type, min, max);
		else if (typeDefNode.getNodeName() == "arraydef") {
			if (_max == "")
				throw new Exception(
						"Attribute max absent de la defnition d array");
			collection = new MsArray(type, max);
		}

		if (collection == null)
			return;

		this.typesDef.put(typeDefNode.getAttribute("id"), collection);
		this.unresolvedObjects.add(collection);
	}


	/**
	 * Check if legacies are correctly build.
	 *
	 * @param entities
	 *            the entities
	 */

	private void verifHeritage(MsEntity fils){
		
		MsType parent = fils.getParent();

		if (parent != null) {
			// gestion heritage circulaire
			MsEntity parentEntity = this.entities.get(parent.getTypeName());
			if (parentEntity != null) {
				MsType typeParent = parentEntity.getParent();
				if (typeParent != null) {
					String parentTypeName = typeParent.getTypeName();
					if (parentTypeName != null) {
						if (parentTypeName.equals(fils.getName())) {
							parentEntity.setParent(null);
							System.out
									.println("Circularity Error : double héritage entre "
											+ parentTypeName
											+ " et "
											+ parent.getTypeName());
						}
					}
				}
			}

			// gestion definition multiple

			for (MsAttribute attrib : parentEntity.getAttributes()) {
				MsAttribute attribDelete=null;
				for (MsAttribute attrib2 :fils.getAttributes() ) {
					if(attrib.getName().equals(attrib2.getName())){
						attribDelete=attrib2;
						System.out.println("Multiple definition Error : l'attribut " + attrib2.getName()+" est présent dans les classes "+fils.getName()+" et "+parentEntity.getName());
						
					}
				}if(attribDelete!=null){
					fils.getAttributes().remove(attribDelete);
				}
			}
			verifHeritage(parentEntity);	
		}
	}
	


	/**
	 * Resolve unresolved types.
	 */
	private void resolveTypes() {
		for (UnresolveObject object : this.unresolvedObjects)
			object.resolve(typesDef, entities);
	}
}
