package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;


public abstract class MsType {
	boolean isPrimitif;
	public abstract String getTypeName();

	public abstract void accept(Visitor visitor);
	
}
