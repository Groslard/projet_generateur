package modelMiniSpec;

import generator.JavaTypeNameVisitor;
import generator.JavaVisitor;
import generator.Visitor;


public abstract class MsType {

	public abstract String getTypeName();

	public abstract void accept(Visitor visitor);
	
}
