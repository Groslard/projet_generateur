package GrosPaquet; 

public class Balise { 

	private String monstring; 
	private Arraylist<Arraylist<Coucou>> mescoucou; 
	private Arraylist<String> noms; 

	public Balise(){} 

	public void setMonString(String monstring){
		this.monstring=monstring;
	}

	public String getMonString(){
		return monstring;
	}

	public void setMesCoucou(Arraylist<Arraylist<Coucou>> mescoucou){
		this.mescoucou=mescoucou;
	}

	public Arraylist<Arraylist<Coucou>> getMesCoucou(){
		return mescoucou;
	}

	public void setNoms(Arraylist<String> noms){
		this.noms=noms;
	}

	public Arraylist<String> getNoms(){
		return noms;
	}


 }