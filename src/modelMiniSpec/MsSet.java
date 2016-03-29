package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;

public class MsSet extends MsCollection {

	public MsSet(String id, MsType type) {
		super();
		this.type = type;
	}

	public MsSet( MsType type, int min, int max) {
		super();
		this.type = type;
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return "MsSet [type=" + type + ", min=" + min + ", max=" + max + "]";
	}
	
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "set";
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
}
