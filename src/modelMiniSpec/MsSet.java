package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;

/**
 * The Class MsSet.
 */
public class MsSet extends MsCollection {

	/**
	 * Instantiates a new ms set.
	 *
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 */
	public MsSet(String id, MsType type) {
		super();
		this.type = type;
	}

	/**
	 * Instantiates a new ms set.
	 *
	 * @param type
	 *            the type
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 */
	public MsSet(MsType type, int min, int max) {
		super();
		this.type = type;
		this.min = min;
		this.max = max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MsSet [type=" + type + ", min=" + min + ", max=" + max + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelMiniSpec.MsType#getTypeName()
	 */
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "set";
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
