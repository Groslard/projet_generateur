package generator;

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;

/**
 * The Class Visitor.
 */
public abstract class Visitor {

	/**
	 * Visit.
	 *
	 * @param o
	 *            the o
	 */
	public abstract void visit(MsModel o);

	/**
	 * Visit.
	 *
	 * @param o
	 *            the o
	 */
	public abstract void visit(MsEntity o);

	/**
	 * Visit.
	 *
	 * @param o
	 *            the o
	 */
	public abstract void visit(MsAttribute o);

	/**
	 * Visit.
	 *
	 * @param list
	 *            the list
	 */
	public abstract void visit(MsList list);

	/**
	 * Visit.
	 *
	 * @param array
	 *            the array
	 */
	public abstract void visit(MsArray array);

	/**
	 * Visit.
	 *
	 * @param set
	 *            the set
	 */
	public abstract void visit(MsSet set);

	/**
	 * Visit.
	 *
	 * @param ref
	 *            the ref
	 */
	public abstract void visit(MsReference ref);
}
