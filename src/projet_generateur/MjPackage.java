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
	
	public static MjPackage getJavaPrimitives(){
		MjPackage primitives = new MjPackage("JavaPrimitives");
		primitives.addEntity(new MjEntity("String"));
		primitives.addEntity(new MjEntity("int"));
		return primitives;
	}
}
