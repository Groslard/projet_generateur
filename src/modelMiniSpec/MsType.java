package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;


public abstract class MsType {
	boolean isPrimitif;
	
	public boolean isPrimitif() {
		return isPrimitif;
	}

	public void setPrimitif(boolean isPrimitif) {
		this.isPrimitif = isPrimitif;
	}


	public abstract String getTypeName();

	public abstract void accept(Visitor visitor);
	
}
