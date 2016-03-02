package modelMiniSpec;

import generator.JavaVisitor;

public class MsPrimitif extends MsType {
	
	public MsPrimitif(String id) {
		super();
		this.id = id;
	}
	
	public MsPrimitif(String id, String defaultValue) {
		super();
		this.id = id;
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "MjPrimitif [id=" + id + "]";
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }
}
