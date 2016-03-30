package Test4; 

import java.util.HashSet;
import java.util.ArrayList;

public class Mot extends ArrayList<ArrayList<String>> { 

	public Mot(){
	 super();
 	 this.noms= new ArrayList<ArrayList<String>>();

 	 this.prenoms=testf();

	} 

	private String monstring; 
	private ArrayList<ArrayList<String>> noms; 
	private HashSet<String> prenoms; 

	public void setMonString(String monstring){
		this.monstring=monstring;
	}

	public String getMonString(){
		return monstring;
	}

	public void setNoms(ArrayList<ArrayList<String>> noms){
		this.noms=noms;
	}

	public ArrayList<ArrayList<String>> getNoms(){
		return noms;
	}

	public void setPrenoms(HashSet<String> prenoms){
		this.prenoms=prenoms;
	}

	public HashSet<String> getPrenoms(){
		return prenoms;
	}

	public void addNoms(ArrayList<String> list){
			this.noms.add(list);
	}

	public void  removeNoms(ArrayList<String> list){
		 this.noms.remove(list);
	}

	public void addPrenoms(String string){
			this.prenoms.add(string);
	}

	public void  removePrenoms(String string){
		 this.prenoms.remove(string);
	}

	 public HashSet<String> testf(){
	 return null;
}
 }