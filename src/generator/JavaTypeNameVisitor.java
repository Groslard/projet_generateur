package generator;

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;
import modelParameter.PrmConfig;

public class JavaTypeNameVisitor extends Visitor{

	private String result;
	private PrmConfig conf;
	
	public JavaTypeNameVisitor(PrmConfig conf) {
		super();
		this.conf = conf;
		this.result="";
	}

	public void visit(MsReference msReference) {
		
		result+=msReference.getTypeName();
	}
	
	public void visit(MsList msList) {
		result+=conf.getParameterPrimitif(msList.getTypeName()).getType()+"<";
		msList.getType().accept(this);
		result+=">";
		
	}
	
	public void visit(MsSet msSet) {
		result+=conf.getParameterPrimitif(msSet.getTypeName()).getType()+"<";
		msSet.getType().accept(this);
		result+=">";
		
	}
	
	@Override
	public void visit(MsArray array) {
		result+=array.getType().getTypeName()+"[]";
		
	}
	
	public String getResult() {
		String res = result;
		result = "";
		return res;
	}
	
	
	
	@Override
	public void visit(MsModel o) {}
	@Override
	public void visit(MsEntity o) {}
	@Override
	public void visit(MsAttribute o) {}
	

	
	
}
