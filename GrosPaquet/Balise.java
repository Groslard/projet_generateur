package GrosPaquet; 

public class Balise { 

	private Arraylist<Arraylist<String>> liste_a; 
	private String monstring; 

	public Balise(){} 

	public void setListe_a(Arraylist<Arraylist<String>> liste_a){
		this.liste_a=liste_a;
	}

	public Arraylist<Arraylist<String>> getListe_a(){
		return liste_a;
	}

	public void setMonString(String monstring){
		this.monstring=monstring;
	}

	public String getMonString(){
		return monstring;
	}


 }