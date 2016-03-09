package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;

public class MsList extends MsCollection {

	public MsList(String id, MsType type) {
		super();
		this.type = type;
	}

	public MsList( MsType type, int min, int max) {
		super();
		this.type = type;
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return "MsList [type=" + type + ", min=" + min + ", max=" + max + "]";
	}
	
	 public void accept(Visitor visitor){
		 visitor.visit(this);
	 }
	 
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "list";
	}


}
