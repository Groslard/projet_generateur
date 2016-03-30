package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;

/**
 * The Class MsArray.
 */
public class MsArray extends MsCollection {

	/**
	 * Instantiates a new ms array.
	 *
	 * @param type
	 *            the type
	 * @param max
	 *            the max length
	 */
	public MsArray(MsType type, int max) {
		super();
		this.type = type;
		this.max = max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MsArray [type=" + type + ", size=" + max + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#accept(generator.Visitor)
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#getTypeName()
	 */
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "array";
	}
}
