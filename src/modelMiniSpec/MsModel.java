package modelMiniSpec;

import generator.JavaVisitor;

import java.util.ArrayList;

public class MsModel {
	String name;
	ArrayList<MsEntity> entities;
	
	public MsModel(String name){
		this.name = name;
		entities = new ArrayList<MsEntity>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addEntity(MsEntity entity) {
		entities.add(entity);
	}
	
	public MsEntity getEntity(String name){
		for(MsEntity entity:entities){
			if(entity.name.equals(name))
				return entity;
		}
		return null;
	}
	
	public ArrayList<MsEntity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<MsEntity> entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		String res = "MjPackage [name=" + name + ", \nentities=";
		for(MsEntity entity : entities)
			res+="\n"+entity;
		return res;
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }
}
