package generator;

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;
import modelParameter.PrmConfig;

/**
 * The Class JavaTypeNameVisitor.
 */
public class JavaTypeNameVisitor extends Visitor {

	/** The result. */
	private String result;
	private String constructor;

	/** The conf. */
	private PrmConfig conf;

	/**
	 * Instantiates a new java type name visitor.
	 *
	 * @param conf
	 *            the conf
	 */
	public JavaTypeNameVisitor(PrmConfig conf) {
		super();
		this.conf = conf;
		this.result = "";
		this.constructor = "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsReference)
	 */
	public void visit(MsReference msReference) {

		result += msReference.getTypeName();
		constructor = result+"()";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsList)
	 */
	public void visit(MsList msList) {
		result += conf.getParameterPrimitif(msList.getTypeName()).getType()
				+ "<";
		msList.getType().accept(this);
		result += ">";
		constructor = result+"()";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsSet)
	 */
	public void visit(MsSet msSet) {
		result += conf.getParameterPrimitif(msSet.getTypeName()).getType()
				+ "<";
		msSet.getType().accept(this);
		result += ">";
		constructor = result+"()";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsArray)
	 */
	@Override
	public void visit(MsArray array) {
		result += array.getType().getTypeName() + "[]";
		constructor = array.getType().getTypeName()+"["+array.getMax()+"]";
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		String res = result;
		result = "";
		return res;
	}
	
	public String getConstructor(){
		String res = this.constructor;
		this.constructor = "";
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsModel)
	 */
	@Override
	public void visit(MsModel o) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsEntity)
	 */
	@Override
	public void visit(MsEntity o) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsAttribute)
	 */
	@Override
	public void visit(MsAttribute o) {

	}

}
