package modelMiniSpec;

import java.util.ArrayList;

import generator.JavaVisitor;

public class MsAttribute implements UnresolveObject {
	String name;
	MsType type;
	MsEntity entity;
	boolean isConstructor;
	String method;
	String initialValue;
	
	public MsAttribute(String name,MsEntity parentEntity){
		this.name = name;
		this.entity=parentEntity;
		this.initialValue =null;
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
	
	public boolean isConstructor() {
		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
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
