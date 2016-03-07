package modelMiniSpec;

import generator.JavaVisitor;

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
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "set";
	}
}
