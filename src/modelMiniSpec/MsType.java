package modelMiniSpec;

import generator.JavaVisitor;


public abstract class MsType {

	public abstract String getTypeName();

	public abstract void accept(JavaVisitor javaVisitor);
	
}
