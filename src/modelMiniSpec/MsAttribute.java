package modelMiniSpec;

import generator.JavaVisitor;

public class MsAttribute implements UnresolveObject {
	String name;
	MsType type;
	MsEntity entity;
	
	public MsAttribute(String name,MsEntity parentEntity){
		this.name = name;
		this.entity=parentEntity;
	}

	public MsEntity getParentEntity() {
		return entity;
	}

	public void setParentEntity(MsEntity parentEntity) {
		this.entity = parentEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MsType getType() {
		return type;
	}

	public void setType(MsType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "\n\tMjAttribute [name=" + name + ", type=" + type + "]";
	}
	
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
		 
	 }

	@Override
	public MsType getUnresolvedType() {
		return type;
	}

	@Override
	public void setResolvedType(MsType resolvedType) {
		this.type = resolvedType;
	}
}
