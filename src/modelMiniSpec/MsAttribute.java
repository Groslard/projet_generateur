package modelMiniSpec;

import java.util.ArrayList;

import generator.JavaVisitor;

public class MsAttribute implements UnresolveObject {
	String name;
	MsType type;
	MsEntity entity;
	
	ArrayList<String> initialValues;
	
	public MsAttribute(String name,MsEntity parentEntity){
		this.name = name;
		this.entity=parentEntity;
		this.initialValues = new ArrayList<String>();
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
	
	public ArrayList<String> getInitialValues() {
		return initialValues;
	}

	public void setInitialValues(ArrayList<String> initialValues) {
		this.initialValues = initialValues;
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
