package projet_generateur;

import java.util.ArrayList;

public class MjEntity {
	String name;
	ArrayList<MjAttribute> attributes;
	
	public MjEntity(String name){
		this.name = name;
		attributes = new ArrayList<MjAttribute>(); 
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
}
