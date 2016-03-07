package modelMiniSpec;

import generator.JavaVisitor;

public class MsArray extends MsCollection {

	public MsArray( MsType type, int max) {
		super();
		this.type = type;
		this.max = max;
	}

	@Override
	public String toString() {
		return "MsArray [type=" + type + ", size=" + max + "]";
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "array";
	}
}
