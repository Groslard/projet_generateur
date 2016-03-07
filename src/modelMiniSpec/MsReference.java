package modelMiniSpec;

import generator.JavaVisitor;

public class MsReference extends MsType {
	MsEntity entity;
	
	public MsReference(MsEntity entity) {
		super();
		this.entity = entity;
	}
	
	public MsReference(MsEntity entity, String defaultValue) {
		super();
		this.entity = entity;
		this.defaultValue = defaultValue;
	}
	
	@Override
	public String toString() {
		return "MjReference [entity=" + getId() + "]";
	}
	
	@Override
	public String getId(){
		return entity.name;
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
	 
}
