package modelMiniSpec;

import generator.JavaVisitor;

public class MsList extends MsCollection {

	public MsList(String id, MsType type) {
		super();
		this.id = id;
		this.type = type;
	}

	public MsList(String id, MsType type, int min, int max) {
		super();
		this.id = id;
		this.type = type;
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return "MsList [type=" + type + ", min=" + min + ", max=" + max + "]";
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }
}
