package modelMiniSpec;

import generator.JavaVisitor;

public class MsArray extends MsCollection {

	public MsArray(String id, MsType type, int max) {
		super();
		this.id = id;
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
}
