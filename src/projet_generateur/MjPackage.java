package projet_generateur;

import java.util.ArrayList;

public class MjPackage {
	String name;
	ArrayList<MjEntity> classes;
	
	public MjPackage(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MjEntity> getClasses() {
		return classes;
	}
	public void setClasses(ArrayList<MjEntity> classes) {
		this.classes = classes;
	}
	
	
}
