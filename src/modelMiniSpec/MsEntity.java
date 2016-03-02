package modelMiniSpec;

import generator.JavaVisitor;

import java.util.ArrayList;

public class MsEntity {
	String name;
	ArrayList<MsAttribute> attributes;
	MsType parent;
	
	public MsEntity(String name){
		this.name = name;
		attributes = new ArrayList<MsAttribute>(); 
		this.parent=null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addAttribute(MsAttribute attribute) {
		attributes.add(attribute);
	}
	
	@Override
	public String toString() {
		return "MjEntity [name=" + name + ", attributes=" + attributes + "]";
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
		 
	 }

	public ArrayList<MsAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<MsAttribute> attributes) {
		this.attributes = attributes;
	}

	public MsType getParent() {
		return parent;
	}

	public void setParent(MsType parents) {
		this.parent = parents;
	}
	 
}
