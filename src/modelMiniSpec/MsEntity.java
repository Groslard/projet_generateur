package modelMiniSpec;

import generator.JavaVisitor;
import generator.Visitor;

import java.util.ArrayList;

public class MsEntity implements UnresolveObject{
	String name;
	ArrayList<MsAttribute> attributes;
	MsType parent;
	MsModel model;
	
	public MsEntity(String name,MsModel model){
		this.name = name;
		attributes = new ArrayList<MsAttribute>(); 
		this.parent=null;
		this.model=model;
	}
	
	public MsModel getModel() {
		return model;
	}

	public void setModel(MsModel model) {
		this.model = model;
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
	
	 public void accept(Visitor visitor){
		 visitor.visit(this);
		 
	 }

	public ArrayList<MsAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<MsAttribute> attributes) {
		this.attributes = attributes;
	}


	public boolean remove(MsAttribute arg0) {
		return attributes.remove(arg0);
	}

	public boolean contains(Object o) {
		return attributes.contains(o);
	}

	public MsType getParent() {
		return parent;
	}

	public void setParent(MsType parents) {
		this.parent = parents;
	}

	@Override
	public MsType getUnresolvedType() {
		return parent;
	}

	@Override
	public void setResolvedType(MsType resolvedType) {
		this.parent = resolvedType;
	}
	 
}
