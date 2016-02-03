package projet_generateur;

import java.util.ArrayList;

public class MjPackage {
	String name;
	ArrayList<MjClass> classes;
	
	public MjPackage(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MjClass> getClasses() {
		return classes;
	}
	public void setClasses(ArrayList<MjClass> classes) {
		this.classes = classes;
	}
	
	
}
