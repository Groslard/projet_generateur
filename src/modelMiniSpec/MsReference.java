package modelMiniSpec;

import generator.JavaVisitor;

public class MsReference extends MsType {
	MsEntity entity;
	
	public MsReference(MsEntity entity) {
		super();
		this.entity = entity;
	}
	

	@Override
	public String toString() {
		return "MjReference [entity="  + getTypeName() + "]";
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }

	public MsEntity getEntity() {
		return entity;
	}

	public void setEntity(MsEntity entity) {
		this.entity = entity;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return this.entity.getName();
	}
	 
}
