package modelMiniSpec;

import generator.Visitor;

/**
 * The Class MsType.
 */
public abstract class MsType {

	/** The is primitif. */
	boolean isPrimitif;

	/**
	 * Checks if is primitif.
	 *
	 * @return true, if is primitif
	 */
	public boolean isPrimitif() {
		return isPrimitif;
	}

	/**
	 * Sets the primitif.
	 *
	 * @param isPrimitif
	 *            the new primitif
	 */
	public void setPrimitif(boolean isPrimitif) {
		this.isPrimitif = isPrimitif;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	public abstract String getTypeName();

	/**
	 * Accept.
	 *
	 * @param visitor
	 *            the visitor
	 */
	public abstract void accept(Visitor visitor);

}
