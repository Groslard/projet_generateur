package modelMiniSpec;

import java.util.ArrayList;

import generator.JavaVisitor;

/**
 * The Class MsAttribute.
 */
public class MsAttribute implements UnresolveObject {

	/** The name. */
	String name;

	/** The type. */
	MsType type;

	/** The entity. */
	MsEntity entity;

	/** The is constructor. */
	boolean isConstructor;

	/** The method. */
	String method;

	/** The initial value. */
	String initialValue;

	/**
	 * Instantiates a new ms attribute.
	 *
	 * @param name
	 *            the name
	 * @param parentEntity
	 *            the parent entity
	 */
	public MsAttribute(String name, MsEntity parentEntity) {
		this.name = name;
		this.entity = parentEntity;
		this.initialValue = null;
	}

	/**
	 * Gets the parent entity.
	 *
	 * @return the parent entity
	 */
	public MsEntity getParentEntity() {
		return entity;
	}

	/**
	 * Sets the parent entity.
	 *
	 * @param parentEntity
	 *            the new parent entity
	 */
	public void setParentEntity(MsEntity parentEntity) {
		this.entity = parentEntity;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public MsType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(MsType type) {
		this.type = type;
	}

	/**
	 * Checks if the attribute needs to be constructed;
	 *
	 * @return true, if is constructor
	 */
	public boolean isConstructor() {
		return isConstructor;
	}

	/**
	 * Sets at true if the attribute should be consctuct at the code generation.
	 *
	 * @param isConstructor
	 *            the new constructor
	 */
	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}

	/**
	 * Gets the method to call to init attribute.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method to call to init attribute.
	 *
	 * @param method
	 *            the new method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Gets the initial value.
	 *
	 * @return the initial value
	 */
	public String getInitialValue() {
		return initialValue;
	}

	/**
	 * Sets the initial value.
	 *
	 * @param initialValue
	 *            the new initial value
	 */
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\tMjAttribute [name=" + name + ", type=" + type + "]";
	}

	/**
	 * Accept.
	 *
	 * @param visitor
	 *            the visitor
	 */
	public void accept(JavaVisitor visitor) {
		visitor.visit(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.UnresolveObject#getUnresolvedType()
	 */
	@Override
	public MsType getUnresolvedType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.UnresolveObject#setResolvedType(modelMiniSpec.MsType)
	 */
	@Override
	public void setResolvedType(MsType resolvedType) {
		this.type = resolvedType;
	}
}
