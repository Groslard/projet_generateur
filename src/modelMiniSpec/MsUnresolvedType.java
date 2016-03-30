package modelMiniSpec;

import generator.Visitor;

/**
 * The Class MsUnresolvedType.
 */
public class MsUnresolvedType extends MsType {

	/** The id. */
	protected String id;

	/**
	 * Instantiates a new ms unresolved type.
	 *
	 * @param id
	 *            the id
	 */
	public MsUnresolvedType(String id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MsUnresolvedType [id=" + id + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#getTypeName()
	 */
	@Override
	public String getTypeName() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#accept(generator.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

}
