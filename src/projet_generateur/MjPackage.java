package projet_generateur;

import java.util.ArrayList;

public class MjPackage {
	String name;
	ArrayList<MjEntity> entities;
	
	public MjPackage(String name){
		this.name = name;
		entities = new ArrayList<MjEntity>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addEntity(MjEntity entity) {
		entities.add(entity);
	}
	
	public MjEntity getEntity(String name){
		for(MjEntity entity:entities){
			if(entity.name.equals(name))
				return entity;
		}
		return null;
	}
	
	@Override
	public String toString() {
		String res = "MjPackage [name=" + name + ", \nentities=";
		for(MjEntity entity : entities)
			res+="\n"+entity;
		return res;
	}
	
	 public void accept(GenerateurVisitor visitor){
		 visitor.visit(this);
	 }
}
