package modelMiniSpec;

import generator.JavaVisitor;
import generator.Visitor;

import java.util.ArrayList;

/**
 * The Class MsEntity.
 */
public class MsEntity implements UnresolveObject {

	/** The name. */
	String name;

	/** The attributes. */
	ArrayList<MsAttribute> attributes;

	/** The parent. */
	MsType parent;

	/** The model. */
	MsModel model;

	/**
	 * Instantiates a new ms entity.
	 *
	 * @param name
	 *            the name
	 * @param model
	 *            the model
	 */
	public MsEntity(String name, MsModel model) {
		this.name = name;
		attributes = new ArrayList<MsAttribute>();
		this.parent = null;
		this.model = model;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public MsModel getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *            the new model
	 */
	public void setModel(MsModel model) {
		this.model = model;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds the attribute.
	 *
	 * @param attribute
	 *            the attribute
	 */
	public void addAttribute(MsAttribute attribute) {
		attributes.add(attribute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MjEntity [name=" + name + ", attributes=" + attributes + "]";
	}

	/**
	 * Accept.
	 *
	 * @param visitor
	 *            the visitor
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);

	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public ArrayList<MsAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes
	 *            the new attributes
	 */
	public void setAttributes(ArrayList<MsAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Removes the.
	 *
	 * @param arg0
	 *            the arg0
	 * @return true, if successful
	 */
	public boolean remove(MsAttribute arg0) {
		return attributes.remove(arg0);
	}

	/**
	 * Contains.
	 *
	 * @param o
	 *            the o
	 * @return true, if successful
	 */
	public boolean contains(Object o) {
		return attributes.contains(o);
	}

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public MsType getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parents
	 *            the new parent
	 */
	public void setParent(MsType parents) {
		this.parent = parents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.UnresolveObject#getUnresolvedType()
	 */
	@Override
	public MsType getUnresolvedType() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.UnresolveObject#setResolvedType(modelMiniSpec.MsType)
	 */
	@Override
	public void setResolvedType(MsType resolvedType) {
		this.parent = resolvedType;
	}

}
