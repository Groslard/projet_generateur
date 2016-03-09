package generator;

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;

public class JavaTypeNameVisitor extends Visitor{

	private String result;


	public JavaTypeNameVisitor() {
		super();
		this.result="";
	}
	public void visit(MsList msList) {
		result+="ArrayList<";
		msList.getType().accept(this);
		result+=">";
		
	}
	public void visit(MsReference msReference) {
		
		result+=msReference.getTypeName();
	}
	
	
	
	public void visit(MsSet msSet) {
		result+="Set<";
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
