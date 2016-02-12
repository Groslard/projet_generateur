package projet_generateur;

import java.util.ArrayList;

public class MjEntity {
	String name;
	ArrayList<MjAttribute> attributes;
	MjType parent;
	
	public MjEntity(String name){
		this.name = name;
		attributes = new ArrayList<MjAttribute>(); 
		this.parent=null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addAttribute(MjAttribute attribute) {
		attributes.add(attribute);
	}
	
	@Override
	public String toString() {
		return "MjEntity [name=" + name + ", attributes=" + attributes + "]";
	}
	
	 public void accept(GenerateurVisitor visitor){
		 visitor.visit(this);
		 
	 }

	public ArrayList<MjAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<MjAttribute> attributes) {
		this.attributes = attributes;
	}

	public MjType getParent() {
		return parent;
	}

	public void setParent(MjType parents) {
		this.parent = parents;
	}
	 
}
