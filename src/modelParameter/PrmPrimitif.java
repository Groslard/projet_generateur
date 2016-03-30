package modelParameter;

/**
 * The Class PrmPrimitif.
 */
public class PrmPrimitif extends PrmParameter {

	/** Boolean that indicate if parameter is primitif. */
	boolean primitif;
	/** The type. */
	String type;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Checks if is primitif.
	 *
	 * @return true, if is primitif
	 */
	public boolean isPrimitif() {
		return primitif;
	}

	/**
	 * Sets the primitif.
	 *
	 * @param primitif
	 *            the new primitif
	 */
	public void setPrimitif(boolean primitif) {
		this.primitif = primitif;
	}

}
