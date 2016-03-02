package modelMiniSpec;

import generator.JavaVisitor;

public class MsAttribute {
	String name;
	MsType type;
	
	public MsAttribute(String name){
		this.name = name;
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
}
