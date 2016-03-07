package modelMiniSpec;

import generator.JavaVisitor;

public class MsUnresolvedType extends MsType {
	protected String id;
	public MsUnresolvedType(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MsUnresolvedType [id=" + id + "]";
	}

	@Override
	public String getTypeName() {
		return id;
	}

	@Override
	public void accept(JavaVisitor javaVisitor) {
		// TODO Auto-generated method stub
		
	}
	
	
}
