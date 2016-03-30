package modelMiniSpec;

/**
 * The Class MsCollection.
 */
public abstract class MsCollection extends MsType implements UnresolveObject {

	/** The type. */
	MsType type;

	/** The min. */
	// minimum variables in the list
	int min;

	/** The max. */
	// Maximum variables in the list
	int max;

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
	 * Gets the min.
	 *
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Sets the min.
	 *
	 * @param min
	 *            the new min
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Gets the max.
	 *
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Sets the max.
	 *
	 * @param max
	 *            the new max
	 */
	public void setMax(int max) {
		this.max = max;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.UnresolveObject#getUnresolvedType()
	 */
	@Override
	public MsType getUnresolvedType() {
		return this.type;
	}

}
