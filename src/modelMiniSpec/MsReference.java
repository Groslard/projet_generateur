package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;

/**
 * The Class MsReference.
 */
public class MsReference extends MsType {

	/** The entity. */
	MsEntity entity;

	/**
	 * Instantiates a new ms reference.
	 *
	 * @param entity
	 *            the entity
	 */
	public MsReference(MsEntity entity) {
		super();
		this.entity = entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MsReference [entity=" + getTypeName() + "]";
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

	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	public MsEntity getEntity() {
		return entity;
	}

	/**
	 * Sets the entity.
	 *
	 * @param entity
	 *            the new entity
	 */
	public void setEntity(MsEntity entity) {
		this.entity = entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#getTypeName()
	 */
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return this.entity.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#accept(generator.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);

	}

}
