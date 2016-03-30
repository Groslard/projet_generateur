package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;

/**
 * The Class MsList.
 */
public class MsList extends MsCollection {

	/**
	 * Instantiates a new ms list.
	 *
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 */
	public MsList(String id, MsType type) {
		super();
		this.type = type;
	}

	/**
	 * Instantiates a new ms list.
	 *
	 * @param type
	 *            the type
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 */
	public MsList(MsType type, int min, int max) {
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
		return "MsList [type=" + type + ", min=" + min + ", max=" + max + "]";
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
		return "list";
	}

}
